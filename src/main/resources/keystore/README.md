# Example certificates

## Disclaimer
**This is not for production use !!**

These certificates (both public and private), and all passwords, have been published to the world.


#### Create Issuer's certificates (the CA)

```bash
openssl genrsa -out local_ca.key 4096
openssl req -new -x509 -days 36500 -key local_ca.key -out local_ca.crt -subj "/C=US/ST=VA/L=Springfield/O=Springfield Nuclear Power Plant/OU=Sector 7G/CN=homer/emailAddress=homer@simpson.com"
```



#### Create Owner's certificates (the requester)

```bash
openssl genrsa -out server.key 4096
openssl req -new -key server.key -out server.csr -subj "/C=US/ST=VA/L=Springfield/O=Home/OU=742 Evergreen Terrace/CN=marge/emailAddress=marge@simpson.com"
openssl x509 -req -days 3650 -in server.csr -CA local_ca.crt -CAkey local_ca.key -set_serial 01 -out server.crt
```


And for sanity's sake, just check that the certificates you created make sense

```bash
# examine Homer's cert
openssl x509 -in local_ca.crt -text -noout

# examine Marge's cert
openssl x509 -in server.crt -text -noout
```


#### For use with Java Keystores

Convert Owner's public cert and private key into one certificate so we can put it into a java keystore

```bash
openssl pkcs12 -export -in server.crt -inkey server.key -name example -out example.p12 -password pass:changeme
```
