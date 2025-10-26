# template

### Warning!! 💥

This project contains a pre-commit and pre-push hook to have a better code quality. It is
necessary to make this steps in order to setup the quality control at push
times:

1. Change hooksPath

```bash
$ git config core.hooksPath .githooks
```

2. Add permissions to the hook:

```bash
$ chmod +x .git/hooks/pre-commit .git/hooks/pre-push
```

To fix the errors the `pre-commit` hook reports, execute this in the
terminal inside the project's directory (it does not matter if it is the Android
Studio's terminal or your preferred terminal):

```bash
$ ./gradlew detekt
```