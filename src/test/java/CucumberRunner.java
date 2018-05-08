import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features={"C:\\Users\\marcus\\Documents\\MavenCucumberTestProj\\src\\test\\java\\features"},
        features={"src/test/java/features"},tags = {"@regAll"},
        monochrome = true,
        glue = {"Steps"},
        plugin = {"pretty","html:target/cucumber-report","json:target/cucumber-report/cucumber.json",}
)

public class CucumberRunner {


}
