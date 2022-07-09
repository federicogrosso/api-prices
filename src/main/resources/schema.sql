CREATE TABLE `price` (
  `price_id`   bigint NOT NULL,
  `brand_id`   bigint NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date`   datetime NOT NULL,
  `price_list` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `priority`   bigint NOT NULL,
  `price`      double NOT NULL,
  `curr`       varchar(3) NOT NULL,
  PRIMARY KEY (`price_id`)
  );

