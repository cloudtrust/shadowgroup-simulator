# Shadowgroup simulator

This is a test tool for the Cloudtrust project. Its goal is to simulate in a very basic manner the behaviour of one of 
our client's REST servers. It is meant to be used with the client script mappers we added to keycloak to demonstrate 
that we can use them to get attributes from the REST server.

## How to install

Simply build with maven: `mvn clean package`

## How to run

This is a spring boot application, so simply run `java -jar target/shadowgroup-simulator.jar`. The port defaults to 
7080, but this can be changed by changing the `server.port` value in the **application.properties** file, or by
running `java -jar target/shadowgroup-simulator.jar --server.port=<PORT NUMBER>`.

## How to use

To get a value from the simulator simply perform a get on 
`http://<host>:port/shadowgroups/usg/[employeeID]?applicationUrl=[applicationUrl]&mobilityStatus=[mobilityStatus]&jobFunctionCode=[jobFunctionCode]`.
The **referer** header must be set.

The returned json is of the form

```json
{
  "id": "ID",
  "value": ["alpha", "bravo", "charlie", "delta", "[employeeID]", "[applicationUrl]", "[mobilityStatus]"]
}
```