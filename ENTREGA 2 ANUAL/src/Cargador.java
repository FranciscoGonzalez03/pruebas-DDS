import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Cargador {

    public void leerArchivoCsv(String direccionDelCsv) {
        try (FileReader fileReader = new FileReader(direccionDelCsv);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                String nombreOrganismo = csvRecord.get("NombreOrganismo");
                Integer cuit = Integer.parseInt(csvRecord.get("CUIT"));
                String nombrePersona = csvRecord.get("NombrePersonaACargo");
                String apellidoPersona = csvRecord.get("ApellidoPersonaACargo");
                String correoElectronico = csvRecord.get("CorreoElectronico");
                String nombreIdentificador = csvRecord.get("NombreIdentificadorCuenta"); //raro leer el nombre y la contraseña de la cuenta
                String password = csvRecord.get("PasswordCuenta");
                TipoDeNotificacion tipoNotif = TipoDeNotificacion.valueOf(csvRecord.get("TipoNotificacion"));
                FrecuenciaDeNotificacion frecNotif = FrecuenciaDeNotificacion.valueOf(csvRecord.get("FrecuenciaNotificacion"));

                UsuarioGeneral usuario = new UsuarioGeneral(nombrePersona, apellidoPersona, ,correoElectronico); //no le mando la cuenta?
                Cuenta cuenta = new Cuenta(usuario, nombreIdentificador, password); //deberia verificar si la cuenta es valida?
                UsuarioEmpresarial personaACargo = new UsuarioEmpresarial(nombrePersona, apellidoPersona, cuenta, correoElectronico,tipoNotif, frecNotif);
                OrganismoDeControl organismo = new OrganismoDeControl(nombreOrganismo, cuit, personaACargo);

                // Realiza cualquier operación que necesites con el objeto OrganismoDeControl
                System.out.println(organismo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
