package com.example.firmawebsayti.Servise;

import com.example.firmawebsayti.Dto.AypiRespons;
import com.example.firmawebsayti.Dto.IshchiDto;
import com.example.firmawebsayti.Entity.Abstrak.IshchiConsdata;
import com.example.firmawebsayti.Entity.BulimEntity;
import com.example.firmawebsayti.Entity.Ishchi;

import com.example.firmawebsayti.Repozitary.BulimRepozitary;
import com.example.firmawebsayti.Repozitary.IshchiRepozitary;
import com.example.firmawebsayti.Repozitary.LavozimRepozitary;
import com.example.firmawebsayti.Token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IshchiServise  implements UserDetailsService {
@Autowired
IshchiRepozitary ishchiRepozitary;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JavaMailSender getJavaMailSender;
    @Autowired
    Token token;
    @Autowired
    LavozimRepozitary lavozimRepozitary;
@Autowired
    BulimRepozitary bulimRepozitary;
    public AypiRespons registration(IshchiDto ishchiDto) {
        boolean b = ishchiRepozitary.existsByUsername(ishchiDto.getUsername());
        if (b) return new AypiRespons("Bunday faydalanuvchi allaqachon mavjud", false);
        if (ishchiDto.getPassword().equals(ishchiDto.getPassword())){
            Ishchi ishchi=new Ishchi();
            ishchi.setIsmi(ishchiDto.getIsmi());
            ishchi.setFamilyasi(ishchiDto.getFamilyasi());
            ishchi.setUsername(ishchiDto.getUsername());
            Optional<BulimEntity> byId = bulimRepozitary.findById(ishchiDto.getBulimId());
            if (!byId.isPresent()) return new AypiRespons("Bunday bo'lim mavjud emas",false);
            ishchi.setBulimEntity(byId.get());
            ishchi.setPassword(passwordEncoder.encode(ishchiDto.getRepassword()));
            ishchi.setEmailkod(UUID.randomUUID().toString().substring(0, 6));

            ishchi.setLavozim(lavozimRepozitary.findByNomi(IshchiConsdata.USER).get());
            boolean xabaryuborish = Xabaryuborish(ishchi.getUsername(), ishchi.getEmailkod());
           if (xabaryuborish){
               ishchi.setEnabled(true);
               ishchiRepozitary.save(ishchi);
               return new AypiRespons("Ma'lumot saqlandi",true);
           }
        }
        return new AypiRespons("Parolllar mos kelmadi",false);
    }

    private boolean Xabaryuborish(String email, String emailkod) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("farruxzaitov7176@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject("Confirmation email");
            mailMessage.setText("<a href='http://localhost:8080/user1joyla/emailtasdiqlash?useremail="+email+"&emailkod="+emailkod+"'>hisobni tasdiqlash</a>");
            getJavaMailSender.send(mailMessage);
            return true;
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Ishchi> byUsername = ishchiRepozitary.findByUsername(username);
        if (byUsername.isPresent()){
           return  byUsername.get();
        }
        return (UserDetails) new UsernameNotFoundException("username topilmadi");
    }

    public AypiRespons login(IshchiDto ishchiDto) {
        Optional<Ishchi> byUsername = ishchiRepozitary.findByUsername(ishchiDto.getUsername());
        try {
            if (byUsername.isPresent()){
                Ishchi users=new Ishchi();
                if(users.getEmailkod()==null){
                    Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(ishchiDto.getUsername(), ishchiDto.getPassword()));
                    Ishchi principal = (Ishchi) authenticate.getPrincipal();
                    String s = token.tokin(principal.getUsername(), principal.getLavozim());
                    return new AypiRespons("profilga xush kelibsiz!" + s,true);
                }
                return new AypiRespons("Siz akavutinggizni faollashtiring",true);
            }
            return new AypiRespons("User name yoq",false);
        }
        catch (Exception e) {
            return new AypiRespons("login yoki parol xato", false);
        }
    }

    public AypiRespons faollashtirish(String useremail, String emailkod) {
        Optional<Ishchi> byUsernameAndEmailKod = ishchiRepozitary.findByUsernameAndEmailkod(useremail, emailkod);
        if(byUsernameAndEmailKod.isPresent()){
            Ishchi users = byUsernameAndEmailKod.get();
            users.setEnabled(true);
            users.setEmailkod(null);
            ishchiRepozitary.save(users);
            return new AypiRespons("Hisobingiz faollashtirildi!", true);
        }
        return new AypiRespons("Akkauntingiz allaqachon faolashtirilmadi!",false);
    }

    public AypiRespons JoylashLavizimlarmi(IshchiDto ishchiDto) {
        boolean b=ishchiRepozitary.existsByUsername(ishchiDto.getUsername());
        if (b){
            return new AypiRespons("bunday foydalanovchi allaqachon mavjuddd",false);
        }
        if (ishchiDto.getPassword().equals(ishchiDto.getRepassword())){
            Ishchi users=new Ishchi();
            users.setIsmi(ishchiDto.getIsmi());
            users.setFamilyasi(ishchiDto.getFamilyasi());
            users.setUsername(ishchiDto.getUsername());
            users.setPassword(ishchiDto.getPassword());
            users.setLavozim(lavozimRepozitary.findByNomi(IshchiConsdata.USER).get());

            users.setEnabled(true);
            ishchiRepozitary.save(users);
            return new AypiRespons("Ma'lumot saqlandi",true);
        }
        return new AypiRespons("Parolllar mos kelmadi",false);
    }

    public AypiRespons deleteIshchi(Integer id) {
        ishchiRepozitary.findById(id);
        return new AypiRespons("O'chirildi",true);
    }

    public AypiRespons uqishHam() {
        List<Ishchi> all = ishchiRepozitary.findAll();
        return new AypiRespons(all.toString(),true);
    }
}

