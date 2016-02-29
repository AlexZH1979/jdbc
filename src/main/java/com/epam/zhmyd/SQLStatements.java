package com.epam.zhmyd;

public final class SQLStatements {
    public static final String DROP_SHEMA = "DROP DATABASE `simple`;";
    public static final String CREATE_SHEMA = "CREATE SCHEMA `simple`;";
    public static final String TABLE_USER = "CREATE TABLE `users` (\n" +
            " `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
            " `name` VARCHAR(45) NOT NULL,\n" +
            " `surname` VARCHAR(45) NOT NULL,\n" +
            " `birthdate` DATE NOT NULL,\n" +
            " PRIMARY KEY (`id`),\n" +
            " UNIQUE KEY `id_UNIQUE` (`id`))\n" +
            "  ENGINE = InnoDB DEFAULT CHARSET = utf8";
    public static final String TABLE_FRIENSHIPS ="CREATE TABLE `friendships` (\n" +
            " `user1id` INT(10) UNSIGNED NOT NULL,\n" +
            " `user2id` INT(10) UNSIGNED NOT NULL,\n" +
            " `timestamp` TIMESTAMP(6) NOT NULL,\n" +
            " PRIMARY KEY (`user1id`, `user2id`));";
    public static final String TABLE_POSTS = "CREATE TABLE `posts` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `userId` INT(10) NOT NULL,\n" +
            "  `text` VARCHAR(45) NULL,\n" +
            "  `timestamp` TIMESTAMP(6) NULL,\n" +
            "  PRIMARY KEY (`id`));";
    public static final String INSERT_USER = "INSERT INTO `users` (`name`, `surname`, `birthdate`) VALUES (?, ?, ?);";
    public static final String INSERT_FRIENSHIPS = "INSERT INTO `friendships` (`user1id`, `user2id`, `timestamp`) VALUES (?, ?, ?);";
    public static final String INSERT_POSTS = "INSERT INTO `posts` (`userId`, `text`, `timestamp`) VALUES (?, ?, ?);";
}
