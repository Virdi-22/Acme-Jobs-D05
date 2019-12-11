
    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKoa6p4s2oyy7tf80xwc4r04vh6`;

    alter table `application` 
       drop 
       foreign key `FKmbjdoxi3o93agxosoate4sxbt`;

    alter table `audit_record` 
       drop 
       foreign key `FKdcrrgv6rkfw2ruvdja56un4ji`;

    alter table `audit_record` 
       drop 
       foreign key `FKlbvbyimxf6pxvbhkdd4vfhlnd`;

    alter table `auditor` 
       drop 
       foreign key FK_clqcq9lyspxdxcp6o4f3vkelj;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `banner` 
       drop 
       foreign key `FKjoxwdnjr54soq3j89kt3fgrtj`;

    alter table `comercial_banner` 
       drop 
       foreign key FK_2uqsobmmc3lje3k58op7dsyvw;

    alter table `consumer` 
       drop 
       foreign key FK_6cyha9f1wpj0dpbxrrjddrqed;

    alter table `duty` 
       drop 
       foreign key `FKs2uoxh4i5ya8ptyefae60iao1`;

    alter table `employer` 
       drop 
       foreign key FK_na4dfobmeuxkwf6p75abmb2tr;

    alter table `job` 
       drop 
       foreign key `FK3rxjf8uh6fh2u990pe8i2at0e`;

    alter table `message` 
       drop 
       foreign key `FK3ny0h1379q528toyokq81noiu`;

    alter table `message` 
       drop 
       foreign key `FKn5adlx3oqjna7aupm8gwg3fuj`;

    alter table `non_comercial_banner` 
       drop 
       foreign key FK_h7gdwb5bu1dvickx9h13sl2tj;

    alter table `provider` 
       drop 
       foreign key FK_b1gwnjqm6ggy9yuiqm0o4rlmd;

    alter table `request_auditor` 
       drop 
       foreign key `FK6a462vrps6u2mkgpid1nysqtt`;

    alter table `request_auditor` 
       drop 
       foreign key `FKa6m3imjvm1a1xjc0u4o4dxmks`;

    alter table `sponsor` 
       drop 
       foreign key FK_20xk0ev32hlg96kqynl6laie2;

    alter table `worker` 
       drop 
       foreign key FK_l5q1f33vs2drypmbdhpdgwfv3;

    drop table if exists `administrator`;

    drop table if exists `announcement`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `audit_record`;

    drop table if exists `auditor`;

    drop table if exists `authenticated`;

    drop table if exists `banner`;

    drop table if exists `challenge`;

    drop table if exists `comercial_banner`;

    drop table if exists `company_record`;

    drop table if exists `configuration`;

    drop table if exists `consumer`;

    drop table if exists `duty`;

    drop table if exists `employer`;

    drop table if exists `investor_record`;

    drop table if exists `job`;

    drop table if exists `message`;

    drop table if exists `message_thread`;

    drop table if exists `non_comercial_banner`;

    drop table if exists `offer`;

    drop table if exists `provider`;

    drop table if exists `request_auditor`;

    drop table if exists `sponsor`;

    drop table if exists `user_account`;

    drop table if exists `worker`;

    drop table if exists `_request`;

    drop table if exists `hibernate_sequence`;
