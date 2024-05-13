package com.group.libraryapp.temp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public PersonService(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public void savePerson(){
        //연결되지 않은 테이블이 생성
        Person person = personRepository.save(new Person());
        Address address = addressRepository.save(new Address());

        //Person에 있는 address를 저장해준다. 그러면 DB에 연결된 채로 저장이 된다
        person.setAddress(address);
        //address.setPerson(person);

        //근데 아직 객체끼리는 연결되지 않음

        //트랜잭션이 끝나면 Person과 Address는 연관관계 주인으로 인해 연결이 됐지만
        // 트랜잭션이 끝나기 전에는 객체끼리는 연결되지 않음 그래서 address.getPerson(); 하면 null이 나옴

        address.getPerson();

    }

}
