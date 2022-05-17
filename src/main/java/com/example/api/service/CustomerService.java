package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.domain.Endereco;
import com.example.api.exception.BusinessException;
import com.example.api.form.CustomerForm;
import com.example.api.form.EnderecoForm;
import com.example.api.repository.CustomerRepository;
import com.example.api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class CustomerService {

    private CustomerRepository repository;
    private EnderecoRepository enderecoRepository;

    @Autowired
    public CustomerService(final CustomerRepository repository, final EnderecoRepository enderecoRepository) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
    }

    public List<Customer> findAll() {
        return repository.findAllByOrderByNameAsc();
    }

    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    public void create(final CustomerForm form) {
        this.validarCustomerForm(form, false);

        final Customer customer = new Customer();
        customer.setName(form.getName());
        customer.setEmail(form.getEmail());

        this.repository.save(customer);
    }

    public void put(final CustomerForm form) {
        this.validarCustomerForm(form, true);
        final Optional<Customer> customer = this.findById(form.getId());

        if (customer.isPresent()) {
            final Customer customerObject = customer.get();
            customerObject.setName(form.getName());
            customerObject.setEmail(form.getEmail());

            this.repository.save(customerObject);
        } else {
            throw new BusinessException("Não foi possível encontra o customer desse!");
        }
    }

    public void delete(final Long id) {
        final Optional<Customer> customer = this.findById(id);

        customer.ifPresent(value -> this.repository.delete(value));
    }

    public void cadastrarEndereco(final EnderecoForm form) {
        this.validarEnderecoForm(form);
        final Optional<Customer> customer = this.findById(form.getCustomerId());

        final Endereco endereco = new Endereco();
        endereco.setRua(form.getRua());
        endereco.setNumero(form.getNumero());
        endereco.setBairro(form.getBairro());
        endereco.setCep(form.getCep());
        endereco.setCidade(form.getCidade());
        endereco.setCustomer(customer.get());
        this.enderecoRepository.save(endereco);

    }

    private void validarCustomerForm(final CustomerForm form, final boolean isEdicao) {
        if (isEdicao && isNull(form.getId())) {
            throw new BusinessException("É necessário informar o id do customer!");
        }

        if (isNull(form.getName())) {
            throw new BusinessException("É necessário informar o nome do customer!");
        }

        if (isNull(form.getEmail())) {
            throw new BusinessException("É necessário informar o e-mail do customer!");
        }
    }

    private void validarEnderecoForm(final EnderecoForm form) {
        if (isNull(form.getCustomerId())) {
            throw new BusinessException("É necessário informar o id do customer!");
        }

        if (isNull(form.getRua())) {
            throw new BusinessException("É necessário informar a rua do endereço!");
        }

        if (isNull(form.getNumero())) {
            throw new BusinessException("É necessário informar o número do endereço!");
        }

        if (isNull(form.getBairro())) {
            throw new BusinessException("É necessário informar o bairro do endereço!");
        }

        if (isNull(form.getCep())) {
            throw new BusinessException("É necessário informar o cep do endereço!");
        }

        if (isNull(form.getCidade())) {
            throw new BusinessException("É necessário informar a cidade do customer!");
        }
    }
}
