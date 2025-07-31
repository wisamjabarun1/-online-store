-- USERS TABLE
CREATE TABLE users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  phone VARCHAR(20),
  address VARCHAR(255),
  username VARCHAR(255) UNIQUE,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) DEFAULT 'USER'
);

-- ITEMS TABLE
CREATE TABLE items (
  item_id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  img VARCHAR(1000) NOT NULL,
  price INT NOT NULL,
  quantity INT NOT NULL

);

CREATE TABLE orders (
  order_id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR NOT NULL,
  order_date DATE,
  shipping_address VARCHAR(255) ,
  total_price INT ,
  status ENUM('TEMP', 'CLOSED') NOT NULL DEFAULT 'TEMP',
  FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);

CREATE TABLE order_items (
  order_item_id INT AUTO_INCREMENT PRIMARY KEY,
  order_id INT NOT NULL,
  item_id INT NOT NULL,
  username VARCHAR NOT NULL ,
  price_at_purchase INT NOT NULL,

  FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
  FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE CASCADE,
  FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE

);



CREATE TABLE favorites  (
  id INT AUTO_INCREMENT PRIMARY KEY,
 username VARCHAR NOT NULL,
  item_id INT NOT NULL,
  UNIQUE(username, item_id),
  FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE,
  FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE CASCADE
);





INSERT INTO users (first_name, last_name, email, phone, address, username, password, role) VALUES
('Miki', 'Gabay', 'mikigabay@gmail.com', '0585236376', '45 Ben Eliezer St', 'mikigabay', '$2a$10$24P9JHWZJm8yRsJRpP4a.e11OvU9ynMvz6XAKJOrxl8Nhph7mojJ2', 'USER'),
('Amitay', 'Gabay', 'amitaygabay1@gmail.com', '0504380333', '38 Erez St', 'amitaygabay', '$2a$10$K78Qy75RrDNQcAolPojuM.sI.otXpP23xhZYJ7p2fXrIMoI.k2ehO', 'ADMIN');



INSERT INTO items (title,img,price,quantity) VALUES
('camera','https://images.pexels.com/photos/90946/pexels-photo-90946.jpeg?auto=compress&cs=tinysrgb&w=600',49,10);
INSERT INTO items (title, img, price, quantity) VALUES
('headphones', 'https://cdn.pixabay.com/photo/2016/11/19/16/01/audio-1840073_1280.jpg',29, 15),
('laptop','https://cdn.pixabay.com/photo/2016/11/21/15/46/computer-1846056_1280.jpg' ,599, 7),
('smartphone','https://cdn.pixabay.com/photo/2015/03/01/19/01/smart-phone-655105_1280.jpg',399, 12),
('tablet','https://cdn.pixabay.com/photo/2014/11/25/21/12/tablet-545696_1280.png',199, 9),
('smartwatch', 'https://cdn.pixabay.com/photo/2023/10/07/14/24/smartwatch-8300238_1280.jpg',89, 20),
('keyboard', 'https://cdn.pixabay.com/photo/2024/04/17/01/59/mechanical-keyboard-8701176_1280.png',45, 25),
('mouse', 'https://cdn.pixabay.com/photo/2017/08/28/16/38/computer-mouse-2690350_1280.png',25, 18),
('boots', 'https://cdn.pixabay.com/photo/2019/04/17/10/16/work-boot-4133815_1280.jpg',150, 8),
('printer', 'https://cdn.pixabay.com/photo/2015/05/30/15/45/printer-790396_1280.jpg',120, 5);








