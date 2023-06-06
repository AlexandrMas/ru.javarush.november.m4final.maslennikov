package m4final.maslennikov.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class XMLSessionFactoryProvider implements SessionFactoryProvider {
    @Override
    public SessionFactory getSessionFactory() {
        return new Configuration()
                .configure()
                .buildSessionFactory();
    }
}
