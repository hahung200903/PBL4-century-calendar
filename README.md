1) Khởi chạy chương trình:
  - Server:
  
Server phải luôn được khởi chạy trước các Client.
  
Server chỉ có chức năng xử lý, không có giao diện.
  
Nếu có nhu cầu muốn thay đổi cổng trên Server: tìm đến biến port ở đầu class Server và thay đổi giá trị của nó (cổng mặc định là 2003).
  - Client:
  
Client nên khởi chạy sau khi Server đã hoạt động ổn định (khi Server xuất ra màn hình dòng chữ “Server is ready…”).
  
Gõ một năm bất kỳ vào ô Nhập năm và nhấn nút OK, giao diện sẽ hiển thị lịch của toàn bộ năm đó.
  
Click chuột vào một ngày bất kỳ sẽ hiển thị chi tiết ngày đó: ngày dương lịch, âm lịch, Can – Chi ngày đó.
  
Chú ý: Để Client có thể kết nối đến Server thì cần phải biết địa chỉ và cổng của Server. Nếu có nhu cầu muốn đổi địa chỉ và cổng thì tìm đến biến host và port ở đầu class Client và thay đổi giá trị của nó (nếu Server ở cùng máy với Client thì host mặc định là localhost, port mặc định là 2003).

2) Giao diện chương trình:
- Kết quả chương trình ở Server
 ![image](https://github.com/hahung200903/PBL4-century-calendar/assets/113370985/f3bea7b8-e702-49bb-a55f-658aa1eb3f6e)

- Kết quả kết nối thành công ở Client
![image](https://github.com/hahung200903/PBL4-century-calendar/assets/113370985/fa4b0975-5490-4589-a5c9-6079f9c41034)

- Giao diện lịch vạn niên ở Client
![image](https://github.com/hahung200903/PBL4-century-calendar/assets/113370985/3d25343a-3d9d-4517-89be-0c644599b799)

- Giao diện khi xem chi tiết 1 ngày trong năm ở Client
![image](https://github.com/hahung200903/PBL4-century-calendar/assets/113370985/a541fd71-87f7-4aab-8514-52a0d1749d28)
