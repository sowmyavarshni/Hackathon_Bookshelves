package cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
(
   features= {".//FeatureFile/UrbanLadder.feature"},
   glue= "cucumber",
   plugin = {"pretty","html:Reports/CucumberReport.html"},
   publish=true
)
public class CucumberLauncher
{
   
}
