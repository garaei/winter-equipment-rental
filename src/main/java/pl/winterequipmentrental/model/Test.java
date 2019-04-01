package pl.winterequipmentrental.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.winterequipmentrental.model.address.Address;
import pl.winterequipmentrental.model.address.Province;
import pl.winterequipmentrental.model.branch.Branch;
import pl.winterequipmentrental.model.phone.BranchPhone;
import pl.winterequipmentrental.model.phone.TypePhone;

import java.util.Optional;

@RestController
public class Test {

    @Autowired
    provinceRepository pr;

    @Autowired
    addressRepository ar;

    @Autowired
    branchPhoneRepository bpr;

    @Autowired
    typePhoneRepository tpr;

    @Autowired
    branchRepository br;

    @GetMapping(value = "/")
    public void test() {

//        Province p1 = new Province("lubelskie");
//        Province p2 = new Province("mazowieckie");
//        Address a1 = new Address();
//        Address a2 = new Address();
//
//        pr.save(p1);
//        pr.save(p2);
//
//        a1.setApartmentNumber("6");
//        a1.setBuildingNumber("6");
//        a1.setCity("Lublin");
//        a1.setLocality("Lublin");
//        a1.setStreet("Fantastyczna");
//        a1.setZipCode("20531");
//        a1.setProvince(pr.findByName("lubelskie"));
//
//        a2.setBuildingNumber("17");
//        a2.setCity("Borzechow");
//        a2.setLocality("Dabrowa");
//        a2.setZipCode("24224");
//        a2.setProvince(pr.findByName("lubelskie"));
//
//        ar.save(a1);
//        ar.save(a2);

        Branch b = new Branch("LUBLIN/435/23");
        br.save(b);


        TypePhone tp = new TypePhone();
        tp.setName("SEKRETARIAT");
        tpr.save(tp);

        BranchPhone bp = new BranchPhone();
        BranchPhone bp1 = new BranchPhone();
        bp.setNumberPhone("725107433");
        bp1.setNumberPhone("665434212");

        Optional<TypePhone> typePhone = tpr.findById(2L);
        if (typePhone.isPresent()) {
            bp.setTypePhone(typePhone.get());
            bp1.setTypePhone(typePhone.get());
        }
        if (br.findById(1L).isPresent()) {
            bp.setBranch(br.findById(1L).get());
            bp1.setBranch(br.findById(1L).get());
        }
        bpr.save(bp);
        bpr.save(bp1);
//        b.addBranchPhones(bpr.findById(1L).get());


    }

    @GetMapping(value = "/test")
    public BranchPhone get() {
        Optional<BranchPhone> bprById = bpr.findById(1L);

        Optional<Branch> byId = br.findById(1L);
        if (byId.isPresent()) {
            Branch branch = byId.get();

            for (BranchPhone bp: branch.getBranchPhones())
                System.out.println("Phone: " + bp.getNumberPhone());
        }


        if (bprById.isPresent()) {
            BranchPhone branchPhone = bprById.get();
            return branchPhone;
        } else return null;
    }


}


@Repository
interface provinceRepository extends CrudRepository<Province, Long> {
    Province findByName(String name);
}

@Repository
interface addressRepository extends CrudRepository<Address, Long> {
    @Override
    Optional<Address> findById(Long aLong);
}

@Repository
interface branchPhoneRepository extends CrudRepository<BranchPhone, Long> {
    @Override
    Optional<BranchPhone> findById(Long aLong);

    @Query(value = "SELECT * from telefony_filii where id_telefon = 1", nativeQuery = true)
    BranchPhone find();
}

@Repository
interface typePhoneRepository extends CrudRepository<TypePhone, Long> {
    @Override
    Optional<TypePhone> findById(Long aLong);
}

@Repository
interface branchRepository extends CrudRepository<Branch, Long> {
    @Override
    Optional<Branch> findById(Long aLong);
}

