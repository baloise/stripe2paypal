# index

## set up 
### a workspace

#### in eclipse
```
mvn org.apache.maven.plugins:maven-eclipse-plugin:2.8:eclipse && mvn eclipse:eclipse -DdownloadSources=true
```
### STRIPE API Key

![](requiredRestrictedApiKeyPermissions.png)

### a release

Make sure you use SSH connection to github for auto-tag publishing!

```
mvn release:prepare
```

The actual release artefact is then automatically being build and published via travis by our technical [Baloise Open Source](https://github.com/baopso) user.
