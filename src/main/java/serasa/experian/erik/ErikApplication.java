package serasa.experian.erik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("serasa.experian.erik")
public class ErikApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErikApplication.class, args);
	}

}
