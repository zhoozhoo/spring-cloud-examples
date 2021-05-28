package ca.zhoozhoo.springcloud.keycloak;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServerApplication implements ApplicationRunner {

    protected static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("STARTING THE APPLICATION");
        SpringApplication.run(AuthServerApplication.class, args);
        logger.info("APPLICATION FINISHED");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var serverUrl = args.getOptionValues("serverUrl").get(0);
        var username = args.getOptionValues("username").get(0);
        var password = args.getOptionValues("password").get(0);
        var realm = args.getOptionValues("realm").get(0);

        var keycloak = KeycloakBuilder.builder().serverUrl(serverUrl).realm("master").username(username)
                .password(password).clientId("admin-cli").build();

        for (RealmRepresentation realmRepresentation : keycloak.realms().findAll()) {
            if (realmRepresentation.getRealm().toLowerCase().equalsIgnoreCase("demo".toLowerCase())) {
                logger.info("Realm {} already exist", realm);
                return;
            }
        }

        RealmRepresentation realmRepresentation = new RealmRepresentation();
        realmRepresentation.setRealm(realm);
        keycloak.realms().create(realmRepresentation);

        UserRepresentation user = new UserRepresentation();
        user.setUsername("johndoe");
        keycloak.realm(realm).users().create(user);
    }
}
