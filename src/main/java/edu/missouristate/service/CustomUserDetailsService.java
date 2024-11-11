package edu.missouristate.service;
import edu.missouristate.domain.User;
import edu.missouristate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import edu.missouristate.domain.CustomUserDetails;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUsers = usersRepository.findByEmail(email);
        optionalUsers.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUsers.map(CustomUserDetails::new).get();
    }

    public User getLogginInUser() {
        String email = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((CustomUserDetails) principal).getEmail();
        } else {
            email = principal.toString();
        }
        if (email != null && email.length() > 0) {
            Optional<User> opt = usersRepository.findByEmail(email);
            return (opt.isPresent()) ? opt.get() : null;
        }
        return null;
    }
}