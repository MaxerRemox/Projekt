package pl.Dominik.app;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.Dominik.repo.UserRepo;

public class ProjektUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepo repo;

    public ProjektUserDetailsService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo
                .findByUsernameIgnoreCase(username)
                .map(UserEntityDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
