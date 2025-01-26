# Simple Web Server

This basic web server supports serving static content from the local file
system relative to where your app is deployed. It is also preconfigured to 
support SSL (Just add your trust/key stores as appropriate).

Supports N static sites served from the configured location. Will update the served
content as it is changed on the file system (you can set a cache time if you like).

# Configuration

Easy to configure, just set the context path, resource path, and the allowed file types
that the server should return. For example, the following configuration adds two static apps
"tracker-app" and "another-app":

```
simple-web-server.apps.tracker-app.context-path=tracker-app
simple-web-server.apps.tracker-app.resource-path=web-content/tracker-app
simple-web-server.apps.tracker-app.allowed-file-extensions=html,css,ico,png,svg,js

simple-web-server.apps.another-app.context-path=another-app
simple-web-server.apps.another-app.resource-path=web-content/another-app
simple-web-server.apps.another-app.allowed-file-extensions=html,css,ico,png,svg,js
```

# Ciphering Passwords

The server has Spring Cloud Config as a dependency, which means it supports 
decrypting passwords in property files. Included is a [jar file](./tools/spring-boot-encrypt-cli-tool-1.0.jar) which allows you
to cipher these passwords as needed. 

This is a wrapper around the built-in spring crypto tools. You can find the source on
[GitHub](https://github.com/naeayedea/secret-encryptor)