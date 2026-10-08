# SteekezExchange

A historical Android prototype for tracking collectible-item collections and viewing friends' collections. The Java source separates UI, presenters, interactors, entities, and storage helpers.

## Repository guide

| Path | Purpose |
| --- | --- |
| [app/src/main/java/steekezexchange/yaid/com/steekezexchange/ui/](app/src/main/java/steekezexchange/yaid/com/steekezexchange/ui/) | Collection and friends-list screens. |
| [app/src/main/java/steekezexchange/yaid/com/steekezexchange/mvp/](app/src/main/java/steekezexchange/yaid/com/steekezexchange/mvp/) | Presenter/view interfaces and background collection-loading logic. |
| [app/src/main/java/steekezexchange/yaid/com/steekezexchange/entity/](app/src/main/java/steekezexchange/yaid/com/steekezexchange/entity/) | Collection-item and friend representations. |
| [app/src/main/java/steekezexchange/yaid/com/steekezexchange/utils/FileHelper.java](app/src/main/java/steekezexchange/yaid/com/steekezexchange/utils/FileHelper.java) | Local collection files and friends-list loading. |
| [app/src/main/java/steekezexchange/yaid/com/steekezexchange/database/DataBaseHelper.java](app/src/main/java/steekezexchange/yaid/com/steekezexchange/database/DataBaseHelper.java) | SQLite helper scaffold. |
| [app/src/main/res/](app/src/main/res/) | Layouts, strings, and collectible artwork. |
| [app/build.gradle](app/build.gradle) | Android SDK and dependency declarations. |
| [gradle/wrapper/gradle-wrapper.properties](gradle/wrapper/gradle-wrapper.properties) | Historical Gradle distribution. |

## Historical tooling

The project declares:

- Android compile/target API 21 and minimum API 15.
- Android Build Tools 21.1.2.
- Android Gradle plugin 1.2.3.
- Gradle wrapper 2.2.1.
- Android support-library dependencies from the 21.x/22.x era.

These are the versions recorded in source, rather than a verified installation recipe for a current machine.

## Prototype status

Collection-loading code uses local files. The source does not establish a working online exchange service.

The database helper is incomplete: its `getCollection` method returns a local variable without assigning a value. A successful build or complete application workflow has not been verified. The repository also includes an Android test source, whose presence does not establish a passing run.

The original code and assets are preserved as a record of earlier Android development.
