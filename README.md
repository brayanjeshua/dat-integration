# DAT API

You need Java and gradle in order to run this project.

### Java instalation

Here you can find [Java](https://www.java.com/es/download/) instalation process.

### Gradle instalation

#### Install [SDKMAN](https://sdkman.io/install)

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk version
```

if all went well, the version should be displayed

```bash
sdkman 5.0.0+51
```

#### Then, install gradle or follow this [tutorial](https://gradle.org/install/)

```bash
sdk install gradle 5.2.1
```

## Project execution

On the root folder, run:

```bash
./gradlew task
./gradlew bootrun
```

## Test API

Import collection from json file and test asset post.

- IMPORTANT: Login first, server is the one taking care of session tokens.
