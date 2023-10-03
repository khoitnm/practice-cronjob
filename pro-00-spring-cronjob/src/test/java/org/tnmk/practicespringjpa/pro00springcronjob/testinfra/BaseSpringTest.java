package org.tnmk.practicespringjpa.pro00springcronjob.testinfra;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.tnmk.practicespringjpa.pro00springcronjob.Pro00SpringCronjobApplication;

@ActiveProfiles("test")
@SpringBootTest(classes = { Pro00SpringCronjobApplication.class })
//@ContextConfiguration(initializers = DBContainerContextInitializer.class)
public abstract class BaseSpringTest {
}
