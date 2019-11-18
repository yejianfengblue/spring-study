package com.yejianfengblue.spring.repos;

import com.yejianfengblue.spring.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author yejianfengblue
 */
@Repository
public interface CompanyRepos extends JpaRepository<Company, UUID> {
}
