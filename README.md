# [El Presidente](https://github.com/Nouuu/el-presidente)

| Main status                                                  | Dev status                                                   |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![main version](https://img.shields.io/badge/Main_release_version-1.0-green) | ![main version](https://img.shields.io/badge/Dev_version-1.2-green) |
| [![Build & Test MAIN](https://github.com/Nouuu/el-presidente/actions/workflows/maven.yml/badge.svg)](https://github.com/Nouuu/el-presidente/actions/workflows/maven.yml) [![codecov](https://codecov.io/gh/Nouuu/el-presidente/branch/main/graph/badge.svg?token=MV0CMTYZ2R)](https://app.codecov.io/gh/Nouuu/el-presidente/branch/main/) | [![Build & Test Dev](https://github.com/Nouuu/el-presidente/actions/workflows/main.yml/badge.svg)](https://github.com/Nouuu/el-presidente/actions/workflows/main.yml) [![codecov](https://codecov.io/gh/Nouuu/el-presidente/branch/dev/graph/badge.svg?token=MV0CMTYZ2R)](https://app.codecov.io/gh/Nouuu/el-presidente/branch/dev/) |
| ![Sunburst](https://codecov.io/gh/Nouuu/el-presidente/branch/main/graphs/sunburst.svg?token=MV0CMTYZ2R) | ![Sunburst](https://codecov.io/gh/Nouuu/el-presidente/branch/dev/graphs/sunburst.svg?token=MV0CMTYZ2R) |

El Presidente is a Tropico / Reigns game like where you are a dictator on a island.

<!-- toc -->

- [El Presidente](#el-presidente)
- [Links](#links)
- [Installation](#installation)
  - [Use release](#use-release)
  - [Build from code](#build-from-code)
- [Usage](#usage)
  - [CLI](#cli)
  - [JavaFX](#javafx)
- [Latest version](#latest-version)
  - [Changelog](#changelog)
    - [[1.0.0] - 2021-02-23](#100---2021-02-23)
    - [[1.1.0] - 2021-03-02](#110---2021-03-02)
    - [[1.2.0] - 2021-03-06](#120---2021-03-06)
- [Code](#code)
  - [Dependencies](#dependencies)
  - [Github Actions](#github-actions)
    - [Workflows](#workflows)
    - [Build and test](#build-and-test)
    - [Codecov](#codecov)
  - [Unit tests](#unit-tests)
  - [Core](#core)
    - [Events](#events)
      - [Description of an event and JSON](#description-of-an-event-and-json)
    - [Factions](#factions)
      - [FactionManager](#factionmanager)
    - [Game](#game)
    - [Helper](#helper)
    - [Resources](#resources)
      - [Agriculture](#agriculture)
      - [Industry](#industry)
      - [Finance](#finance)
      - [ResourceManager](#resourcemanager)
    - [Scenario](#scenario)
      - [Sandbox](#sandbox)
      - [Build from JSON](#build-from-json)
    - [Seasons](#seasons)
  - [CLI](#cli-1)
    - [Output](#output)
    - [Input](#input)
  - [JavaFX](#javafx-1)
    - [SceneBuilder](#scenebuilder)
    - [FxApp](#fxapp)
    - [FxController](#fxcontroller)
    - [FxGameManager](#fxgamemanager)
    - [FxMusic](#fxmusic)
      - [FxMusicList](#fxmusiclist)
- [Contributions](#contributions)

<!-- tocstop -->

# Links

Project syllabus : [SyllabusDuProjet.pdf](SyllabusDuProjet.pdf) 

JavaFX for java 11 and above : https://openjfx.io/

How to parse Json file in Java : https://www.baeldung.com/jackson-object-mapper-tutorial

Refactoring : https://refactoring.guru/fr

# Installation

In both case, once you download / generate the jar file, the executable is standalone and you only need to have **Java 11** installed.

## Use release

Download the latest release of the game at : https://github.com/Nouuu/el-presidente/releases

## Build from code

To build from code, maven 3 is needed.

Simply run `mvn install` at the root of the project to generate the latest jar file.

It will be create a jar file named `el_presidente-VERSION.jar` and `el_presidente_VERSION_standalone.jar` at ***/root/folder/target/***

> :warning: You have to use the **standalone** version if the environment don't have javafx or fasterxml installed.

# Usage
to use the app in GUI mode run the .jar by double-click or with command line

If you wan't to run the app in cli mode run the .jar by cli with the option --cli
`java -jar el_presidente_1.0.jar --cli`

## CLI

![Usage of CLI](images/README/el_presidente_CLI.png)
## JavaFX

Once you have started the app in GUI mode, a javaFX window will show up and ask you the first thing to do : Choose the difficulty and the scenario.

> Try to select **Niveau JDG** for more fun :wink:

![image-20210306153515833](images/README/image-20210306153515833.png)

Then the game begin !

You can see on the sidebar real-time informations about the chosen scenario, the difficulty, the resources and faction state.

It also show the minimum satisfaction you have to keep at the end of the year or you will loose.

On the right side, an event is displayed with choice to do. Each choice have consequences so be careful !

![image-20210306154953780](images/README/image-20210306154953780.png)

At the end of the year, depending of the agriculture and industry occupation, you will earn money and food to help you with the next year.

It is the time where you can buy some extra food to avoid famine and bribe some faction to help being above minimum satisfaction. 

![image-20210306155858766](images/README/image-20210306155858766.png)

When you go on next year, you have three case :

- You didn't loose so the scenario continue
- You didn't loose but the scenario is ended (AKA you win)

![image-20210306161110179](images/README/image-20210306161110179.png)

- You loose because of your satisfaction.

![image-20210306161317792](images/README/image-20210306161317792.png)

# [Latest version](https://github.com/Nouuu/el-presidente/releases/tag/1.0)

## Changelog
### [1.0.0] - 2021-02-23
**Added**
- launch of the project in its initial version

### [1.1.0] - 2021-03-02
**Added**
- Easter egg in hardcode
- Refacto of all JavaFX package for better readable 
- Add Play/Pause music button
- Fix scenario details not showing if long text
- Add app icon

### [1.2.0] - 2021-03-06
**Added**

- CLI option to choice Difficulty and Scenario 
- Add a lot of tests for better coverage

# Code

## Dependencies

This project use Maven 3.

| Maven dependencies | Version | Description                                                |
| ------------------ | ------- | ---------------------------------------------------------- |
| Junit              | 4.13.1  | Used for unit testing                                      |
| Cucumber           | 6.9.0   | Used for unit testing                                      |
| AssertJ            | 3.18.1  | Used for unit testing                                      |
| JavaFX             | 15.0.1  | Used for GUI app                                           |
| Apache Commons     | 3.11    | Useful libraries functions                                 |
| Jackson            | 2.12.1  | Help to parse JSON file into Java object                   |
| Jacoco plugin      | 0.8.6   | Generate code coverage report from unit tests              |
| Maven shade plugin | 3.2.4   | Compile code with dependencies to make standalone jar file |
| JavaFX plugin      | 0.0.5   | Add configuration to assign main class on launch           |

## Github Actions

### Workflows

We currently added two workflows on our project.

One is triggered when pushing or making a pull request on **dev** branch, the other works on the same way on **main** branch.

```yaml
name: Build & Test Dev

on:
  push:
    branches: [ dev ]
  pull_request:
    branches: [ dev ]
```
```yaml
name: Build & Test Main

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]
```

Those two workflow do the same thing : build, test and report test coverage.

### Build and test

Once the workflow is triggered, it will :

- Pull the project on the right branch
- Setup JDK 11 on Windows platform
- Search for maven cache from precedent build
- Retrieve missing maven packages
- Run the command `mvn verify` which will build and run unit tests
- Upload Jacoco report on Codecov

```yaml
jobs:
  build:

    runs-on: windows-latest

    steps:
      - name: Pull project
        uses: actions/checkout@v2
      - name: Set up JDK 11
        uses: actions/setup-java@v1
        with:
          java-version: 11
        
      - name: Cache Maven packages
        uses: actions/cache@v2
        with:
          path: ~/.m2
          key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
          restore-keys: ${{ runner.os }}-m2
          
      - name: Build with Maven
        run: mvn --batch-mode --update-snapshots verify
        
      - name: Upload test coverage report on Codecov
        uses: codecov/codecov-action@v1
        with:
          token: ${{ secrets.CODECOV_TOKEN }}
          files: target/site/jacoco/jacoco.xml
```

This will generate a badge that indicate if build and tests were successful : [![Build & Test Dev](https://github.com/Nouuu/el-presidente/actions/workflows/main.yml/badge.svg)](https://github.com/Nouuu/el-presidente/actions/workflows/main.yml)

### Codecov

When test coverage report is uploaded on codecov, it analyze our code coverage and generate badge and chart we can analyze to see our efficient is our code coverage :

[![codecov](https://codecov.io/gh/Nouuu/el-presidente/branch/dev/graph/badge.svg?token=MV0CMTYZ2R)](https://app.codecov.io/gh/Nouuu/el-presidente/branch/dev/)

|                                                              |                                                              |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![image-20210306174549043](images/README/image-20210306174549043.png) | ![image-20210306174620959](images/README/image-20210306174620959.png) |

We can see on each file if each line has been covered, which is just a indicator and not the assurance that our unit test are fine but still very helpful !

![image-20210306174814443](images/README/image-20210306174814443.png)

There is a config file `codecov.yml` that do some check on pull request and push and determine if our code coverage is conform.

It is set to minimum 90% of code coverage with a maximum of 5% variation of coverage between two push

```yaml
codecov:
  require_ci_to_pass: yes

coverage:
  precision: 2
  round: down
  range: "70...100"
  status:
    project:
      default:
        # basic
        target: 90%
        threshold: 5%
        base: auto 
        flags: 
          - unit
        paths: 
          - "src"
       # advanced settings
        branches:
          - main
          - dev
        if_ci_failed: error #success, failure, error, ignore
        informational: false
        only_pulls: false
    patch:
      default:
        # basic
        target: 90%
        threshold: 5%
        base: auto 
        flags: 
          - unit
        paths: 
          - "src"
       # advanced settings
        branches:
          - main
          - dev
        if_ci_failed: error #success, failure, error, ignore
        informational: false
        only_pulls: false

parsers:
  gcov:
    branch_detection:
      conditional: yes
      loop: yes
      method: no
      macro: no

comment:
  layout: "reach,diff,flags,files,footer"
  behavior: default
  require_changes: no
```

## Unit tests

Testing is a central point of the project we have tested the whole Core part of the project. 

We didn't test the CLI and GUI parts because it's the presentation and we consider that it's bound to evolve too often, there's also a time issue that came into the equation.
We preferred to refocus the tests on the logic of the game. 

- Classic unit test (Junit + AssertJ)
We used 2 patterns for the tests :
- Gerhkin [cucumber](https://cucumber.io/)

In summary the Gerhkin tests use a Given When Then structure to enter the tests readable for the non-technical part of the team they also ensure a good separation in the test code.

```
  Scenario: test food price
    Given a game with test scenario in easy
    When i get food price
    Then the food price should be 8
```

We use [Jacoco](https://www.jacoco.org/jacoco/) and [codcov](https://about.codecov.io/) to follow our progress on the tests

![number of test](https://img.shields.io/badge/number%20of%20tests-211-blue)

## Core

The core is the central part of the game. 
It is the non-graphic part.
It is the heart of the game, its logic.
It's the core that will manage the events that come from the different interfaces (GUI / CLI). 

### Events

An event is  is something that happens, it choice choices of el-president and these choices will have consequences that can be negative or positive.

An event can have 1 choice (so no choice in fact) to 4 choices.

The events must be able to influence all the parameters of the game (satisfaction of factions, global satisfaction, the money, industry, agriculture or the number of partisans).

#### Description of an event and JSON

```java
private final Season season;
private final String eventDetails;
private final List<EventChoice> eventChoices;
```

To build our events we use JSON files that describe the details of the event and the possible choices (EventChoice) as well as the impacts it have.

The event choice  have potentially implications on several factions (EventFactionEffect)

```json
{
  "eventDetails": "Un parseur de fichier fait apparition dans le programme.\nCelui-ci est capable de transformer un fichier json en véritable objet java\n",
  "eventChoices": [
    {
      "choiceName": "Tu saute par dessus bord",
      "industryEffect": 7,
      "agricultureEffect": -5,
      "foodEffect": 8,
      "financeEffect": 600,
      "factionEffects": [
        {
          "factionType": "",
          "partisansPercentEffect": 1,
          "satisfactionEffect": -2
        },
      ]
    },
    {
      "choiceName": "Tu reste dans le bato",
      "financeEffect": 60,
      "factionEffects": [
        {
          "factionType": "religieux",
          "partisansPercentEffect": 1,
          "satisfactionEffect": 1
        }
      ]
    }
  ]
}
```
> when factionType is set to "" is that the action conserns all factions

Events are managed by the EventManager

### Factions

The residents of the island are separated into seven factions. They are all enumerated with FactionType :

```java
public enum FactionType {
    capitalist("capitalistes"),
    communist("communistes"),
    liberal("liberaux"),
    religious("religieux"),
    ecologist("écologistes"),
    nationalist("nationalistes"),
    loyalist("loyalistes");

    private final String type;

    FactionType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return type;
    }

    @JsonCreator
    public static FactionType fromString(String string) {
        return Arrays.stream(FactionType.values()).filter(o -> o.type.equalsIgnoreCase(string)).findFirst().orElse(null);
    }
}
```

All of these factions have some partisans and a satisfaction percentage.

If a satisfaction percentage hit 0, it will stick to 0 and will never increase again.

#### FactionManager

Faction manager handle all the faction of the island.

It is an interface that will interact with the faction list and help when some random/batch actions is needed.

```java
public class FactionManager {
    private List<Faction> factionList;
    ...
}
```



### Game

Game is the link between the GUI / CLI and the concrete actions on the resources or factions it also adds the management of time (seasons, years), the triggering of events, it is also here that we will check if the game is lost or not.

This is the last piece of core. It's on the top of the dependency tree.

**Game.java**
```java
public class Game {
    private Difficulty difficulty;
    private TimeManager timeManager;
    private Scenario scenario;
    private FactionManager factionManager;
    private RessourceManager ressourceManager;
    private Event currentEvent;
    private int satisfactionLimit;
```
It is due to this class that we can use the same code for 2 totally different interface systems.
### Helper
Helpers are utility classes that encapsulate the complexity of an operation and give it a name.

We have 2 of them, one for interacting with files, the other for doing mathematical operations.

They include only static methodes

**MathHelper.java**
```java
public class MathHelper {
    private MathHelper() {}

    public static int multiplyIntDoubleToFloor(int intV, double doubleV) {
        return (int) Math.floor(((double) intV) * doubleV);
    }

    public static int divideIntDoubleToFloor(int intV, double doubleV) {
        return (int) Math.floor(((double) intV) / doubleV);
    }

    public static int restrictValue(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
```

### Resources
 The resources are divided into 3 parts:
- Agriculture
- Industry
- Finance

Agriculture and finance are parts of the island. They are measured as a percentage of the island and the sum of all parts of the island may not exceed 100.


#### Agriculture

agriculture generates a number of food units each year corresponding to 40 times the percentage of island dedicated to agriculture

#### Industry

The industry generates money at the end of each year corresponding to 10 times the percentage of the island dedicated to industry.
#### Finance

Finance represents the money available in the coffers of the republic. This money can be used to buy food supplements to protect against famine, or to pay bribes to factions.
#### ResourceManager

A Resource Manager is used to make an interface between the game and the different resources. It also allows to manage the different resources and the interactions between them.

### Scenario

The scenario is one of the most important part of the game. It load everything when a new game is started.

It contain the initial money, food, partisans, satisfaction, events, etc...

To be more flexible, each scenario are saved into JSON files that are parsed when you chose the right one at the beginning of the game.

#### Sandbox

Sandbox is inherit of Scenario and the difference is that include a lot of more events than a classic scenario, and each turn it will pick one randomly according to the current Season.

Where a Scenario is completed when you reach the last event, a Sandbox finish only when you loose. 

#### Build from JSON

We use jackson-databind library to map JSON file into instantiated object. 

To do that, we use our FileHelper to get the content of the given JSON file, then with some annotations on the object constructor, we map the properties of JSON into properties for constructor.

```java
protected Scenario(@JsonProperty("introduction") String introduction,
                    @JsonProperty("partisansSatisfaction") int partisansSatisfaction,
                    @JsonProperty("partisans") int partisans,
                    @JsonProperty("loyalistPartisansSatisfaction") int loyalistPartisansSatisfaction,
                    @JsonProperty("loyalistPartisans") int loyalistPartisans,
                    @JsonProperty("events") List<String> events,
                    @JsonProperty("initialMoney") int initialMoney,
                    @JsonProperty("initialFood") int initialFood,
                    @JsonProperty("initialIndustrialization") int initialIndustrialization,
                    @JsonProperty("initialAgriculture") int initialAgriculture) throws JsonProcessingException {

        this.introduction = introduction;
        this.initialPartisansSatisfaction = partisansSatisfaction;
        this.initialLoyalistPartisansSatisfaction = loyalistPartisansSatisfaction;
        this.initialPartisans = partisans;
        this.initialLoyalistPartisans = loyalistPartisans;
        this.initialMoney = initialMoney;
        this.initialFood = initialFood;
        this.initialIndustrialization = initialIndustrialization;
        this.initialAgriculture = initialAgriculture;
        this.eventManager = new EventManager(getEvents(events));

    }

```

If the JSON file is incorrect, it will throw a `JsonProcessingException` that we catch.

### Seasons

One season corresponds to one round of the game

The season is linked to an event because all season have an event

Seasons is represented by an enum, which lists the 4 seasons of a year, we use it for loop over it in a year

```java
public enum Season {
    spring("printemps"),
    summer("été"),
    autumn("automne"),
    winter("hiver");

    private final String season;

    Season(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return season;
    }

    @JsonCreator
    public static Season fromString(String string) {
        return Arrays.stream(Season.values()).filter(o ->
            o.season                    	  
            .equalsIgnoreCase(string))
            .findFirst().orElse(null);
    }
}
```

## CLI
The CLI is considered to be the development mode.
The CLI is java vanilla, we didn't want to add a library for this part.

### Output
The CLI is not very verbose on the possible options for event of end of year actions
At the end of the year you get optional reports with the Resources info and the Factions info

### Input
The CLI is based on **Scanner** You must write exactly what is expected

## JavaFX

### SceneBuilder

### FxApp

### FxController

### FxGameManager

### FxMusic
Fx music is a class that is part of GUI (made in javaFX) that contains functions related to sound management in the interface.

It contains an audio player and a playlist that loops
```java
public class FxMusic {

    private MediaPlayer player;
    private final List<Media> playlist;
```

#### FxMusicList

FxMusicList is an enum that contains all the music and the path to the file
```java
public enum FxMusicList {
    GLORIOUS_MORNING("/music/glorious-morning.mp3"),
    GLORIOUS_MORNING_2("/music/glorious-morning-2.mp3");
```
# Contributions

|                                                  |                                                              |
| ------------------------------------------------ | ------------------------------------------------------------ |
| [Noé LARRIEU-LACOSTE](https://github.com/Nouuu)  | [![followers](https://img.shields.io/github/followers/nouuu)]((https://github.com/Nouuu)) |
| [Swann HERRERA](https://github.com/SwannHERRERA) | [![followers](https://img.shields.io/github/followers/SwannHERRERA)](https://github.com/SwannHERRERA) |
