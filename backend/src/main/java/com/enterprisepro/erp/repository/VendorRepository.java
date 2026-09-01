package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByVendorCode(String vendorCode);
    Optional<Vendor> findByEmail(String email);
    List<Vendor> findByActiveTrue();
    Page<Vendor> findByCompanyNameContainingIgnoreCaseOrVendorCodeContainingIgnoreCaseOrContactPersonContainingIgnoreCase(
            String companyName, String vendorCode, String contactPerson, Pageable pageable);
}
