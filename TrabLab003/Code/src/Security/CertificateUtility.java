package Security;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CertificateUtility {

    public static X509Certificate getCertificate(byte[] certificateBytes) throws Exception {
        InputStream certificateInputStream = new ByteArrayInputStream(certificateBytes);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        return (X509Certificate) certificateFactory.generateCertificate(certificateInputStream);
    }

    public static String getCertificateCamp(X509Certificate x509Certificate, String campName) throws Exception {
        String regex = ".*" + campName + "=([^,]*).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(x509Certificate.getSubjectDN().toString());
        matcher.matches();

        return matcher.group(1);
    }

    public static String getCertificateEMAILADDRESS(X509Certificate x509Certificate) throws Exception {
        return getCertificateCamp(x509Certificate, "EMAILADDRESS");
    }

    public static String getCertificateEMAILADDRESS(byte[] certificateBytes) throws Exception {
        return getCertificateEMAILADDRESS(getCertificate(certificateBytes));
    }

    public static String getCertificateCN(X509Certificate x509Certificate) throws Exception {
        return getCertificateCamp(x509Certificate, "CN");
    }

    public static String getCertificateCN(byte[] certificateBytes) throws Exception {
        return getCertificateCN(getCertificate(certificateBytes));
    }
}
