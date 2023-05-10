import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        TesteCadastro.class,
        TesteRegrasCadastro.class,
        TesteCampoTreinamento.class
})
public class SuiteTeste {

}
