# Snuffles - DEPRECATED

Snuffles is a Java based bot for [dubtrack.fm](https://www.dubtrack.fm) based upon enhancing the user experience of dubtrack through a number of features. Snuffles is also based on and shamelessly copies functionality from [EmancipatorBot](https://github.com/chrishayesmu/EmancipatorBot)

The existing functionality includes:

* Chat Commands including but not limited to:
    * !stats
    * !btfo
    * !meme

* Chat mirroring to IRC
* Chat messages on specific user join
* Stat monitoring of users, their track plays and their votes
* Detection of repeated recent track plays
* Detection of a configurable percentage of the room downdubbing

# Technologies

* Spring Boot
* Spring Data JPA
* Log4j2
* Liquibase
* PircBot
* PubNub

# Dependencies

* JDK 1.8
* Gradle 2.10
* PostgreSQL 9.2+