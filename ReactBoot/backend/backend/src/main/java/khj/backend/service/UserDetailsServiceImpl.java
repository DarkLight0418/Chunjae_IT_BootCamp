package khj.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import khj.backend.domain.Reuser;
import khj.backend.repository.ReuserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ReuserRepository reuserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Reuser>  optionalReuser = reuserRepository.findByUsername(username);

        User.UserBuilder userBuilder = null;
        if(optionalReuser.isPresent()) {
            Reuser cuser = optionalReuser.get(); //현재 user
            userBuilder = User.withUsername(username);
            userBuilder.password(cuser.getPassword());
            userBuilder.roles(cuser.getRole());
        }else{
            throw new UsernameNotFoundException(username+"이란 사용자는 발견되지 않음");
        }

        return userBuilder.build();
    }
}