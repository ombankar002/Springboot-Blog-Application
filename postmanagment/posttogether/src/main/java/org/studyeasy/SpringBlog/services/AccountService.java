package org.studyeasy.SpringBlog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.studyeasy.SpringBlog.models.Account;
import org.studyeasy.SpringBlog.models.Authority;
import org.studyeasy.SpringBlog.repositories.AccountRepository;
import org.studyeasy.SpringBlog.util.constants.Roles;

@Service
public class AccountService implements UserDetailsService {

    @Value("${spring.mvc.static-path-pattern}")
    private String photo_prefix;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        if (account.getRole() == null) {
            account.setRole(Roles.USER.getRole());
        }
        if (account.getPhoto() == null) {
            String path = photo_prefix.replace("**", "images/person.png");
            account.setPhoto(path);
        }

        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findOneByEmailIgnoreCase(email);
        if (optionalAccount.isEmpty()) {
            throw new UsernameNotFoundException("Account not found");
        }
        Account account = optionalAccount.get();

        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority(account.getRole()));

        Set<Authority> authorities = account.getAuthorities();
        for (Authority _auth : authorities) {
            grantedAuthority.add(new SimpleGrantedAuthority(_auth.getName()));
        }

        return new User(account.getEmail(), account.getPassword(), grantedAuthority);
    }

    public Optional<Account> findOneByEmail(String email) {
        return accountRepository.findOneByEmailIgnoreCase(email);
    }

    public Optional<Account> findById(long id) {
        return accountRepository.findById(id);
    }

    public Optional<Account> findByToken(String token) {
        return accountRepository.findByToken(token);
    }

}
