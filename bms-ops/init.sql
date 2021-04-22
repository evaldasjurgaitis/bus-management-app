CREATE database bms_customer_service
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
CREATE USER 'bms_customer_service_user'@'%' IDENTIFIED BY 'bms_customer_service_password';
GRANT ALL PRIVILEGES ON bms_customer_service.* TO 'bms_customer_service_user'@'%' WITH GRANT OPTION;

CREATE database bms_account_service
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
CREATE USER 'bms_account_service_user'@'%' IDENTIFIED BY 'bms_account_service_password';
GRANT ALL PRIVILEGES ON bms_account_service.* TO 'bms_account_service_user'@'%' WITH GRANT OPTION;

CREATE database bms_booking_service
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
CREATE USER 'bms_booking_service_user'@'%' IDENTIFIED BY 'bms_booking_service_password';
GRANT ALL PRIVILEGES ON bms_booking_service.* TO 'bms_booking_service_user'@'%' WITH GRANT OPTION;

CREATE database bms_bus_service
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
CREATE USER 'bms_bus_service_user'@'%' IDENTIFIED BY 'bms_bus_service_password';
GRANT ALL PRIVILEGES ON bms_bus_service.* TO 'bms_bus_service_user'@'%' WITH GRANT OPTION;