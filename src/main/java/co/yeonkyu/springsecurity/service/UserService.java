package co.yeonkyu.springsecurity.service;

import co.yeonkyu.springsecurity.dto.UserInfoDto;
import co.yeonkyu.springsecurity.entity.UserInfo;
import co.yeonkyu.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    /**
     * 회원 정보 저장 : 입력받은 패스워드를 BCrypt 로 암호화 한 후에 회원을 저장한다.
     * @param infoDto
     * @return
     */
    public Long save(UserInfoDto infoDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        // 생성자로 해도 될 듯 하다.
        return userRepository.save(UserInfo.builder()
                .email(infoDto.getEmail())
                .auth(infoDto.getAuth())
                .password(infoDto.getPassword()).build()).getCode();
    }


    /**
     * Spring Security 필수 메소드 구현
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException {

        // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException((email)));
    }
}
