package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        TypedQuery<Employee> theQuery=entityManager.createQuery("From Employee",Employee.class);

        List<Employee> employees=theQuery.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        Employee the =entityManager.find(Employee.class,theId);
        return the;
    }

    @Override
    public Employee save(Employee theEmployee) {

        Employee the=entityManager.merge(theEmployee);

        return the;
    }

    @Override
    public void deleteById(int theId) {
        Employee theEmployee=entityManager.find(Employee.class,theId);
        entityManager.remove(theEmployee);

    }
}
