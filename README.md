# Tugas 2 IF3110 Pengembangan Aplikasi Berbasis Web

Melakukan *upgrade* Website Marketplace sederhana pada Tugas 1 dengan mengaplikasikan **arsitektur web service REST dan SOAP**.


### Tujuan Pembuatan Tugas

Tujuan pembuatan tugas ini adalah untuk memahami:
* Produce dan Consume REST API
* Mengimplementasikan service Single Sign-On (SSO) sederhana
* Produce dan Consume Web Services dengan protokol SOAP
* Membuat web application dengan menggunakan JSP yang akan memanggil web services dengan SOAP dan REST.

### Petunjuk Pengerjaan
## Anggota Tim
**Kelompok Seterah**
1. Vitra Chandra    /   13514043
2. Steffi Indrayani /   13514063
3. Diastuti Utami   /   13514071

## Petunjuk Pengerjaan

8. [DELIVERABLE] Berikan penjelasan mengenai hal di bawah ini pada bagian **Penjelasan** dari *readme* repository git Anda:
    - Basis data dari sistem yang Anda buat.
    - Konsep *shared session* dengan menggunakan REST.
    - Pembangkitan token dan expire time pada sistem yang anda buat.
    - Kelebihan dan kelemahan dari arsitektur aplikasi tugas ini, dibandingkan dengan aplikasi monolitik (login, CRUD DB, dll jadi dalam satu aplikasi)
9. Pada *readme* terdapat penjelasan mengenai pembagian tugas masing-masing anggota (lihat formatnya pada bagian **pembagian tugas**).
10. Merge request dari repository anda ke repository ini dengan format **Nama kelompok** - **NIM terkecil** - **Nama Lengkap dengan NIM terkecil** sebelum **Minggu, 13 November 2016 23.59**.

### Arsitektur Umum Server
![Gambar Arsitektur Umum Server](http://gitlab.informatika.org/IF3110_WebBasedDevelopment_2016/TugasBesar2_JavaAndWebService/raw/3747ba2499396d04f742a589a024876964383159/arsitektur_umum.png)

Tugas 2 ini terdiri dari berberapa komponen yang harus dibuat:
* `Web app`: digunakan untuk menangani HTTP request dari web browser dan menghasilkan HTTP response. Bagian yang diimplementasi dengan JSP ini juga bertugas untuk meng-generate tampilan web layaknya PHP. Bagian ini wajib dibuat dengan **Java + Java Server Pages**. Maka dari itu, konversi seluruh kode PHP pada tugas 1 menjadi kode JSP.
* `Marketplace Web Service`: digunakan sebagai interface yang dipanggil oleh aplikasi melalui protokol SOAP. melakukan query ke database, operasi insert, dan operasi update untuk entitas User, Question, dan Answer. Webservice ini akan dipanggil oleh aplikasi dengan menggunakan SOAP. Webservice ini wajib dibuat dengan **JAX-WS dengan protokol SOAP atau Webservice lain** yang basisnya menggunakan **SOAP dan Java**.
* `Identity service`: dipanggil oleh aplikasi untuk menerima email (sebagai username) dan password pengguna, dan menghasilkan *access token*. Identity Service juga dipanggil oleh *marketplace web service* untuk melakukan validasi terhadap *access token* yang sedang dipegang oleh *web app*. Service ini dibuat menggunakan REST. Identity service ini wajib dibuat dengan menggunakan **Java Servlet**.
* `Database`: pisahkan basis data yang telah Anda buat pada tugas 1 menjadi basis data khusus manajemen *account* (menyimpan username, password, dkk) dan basis data marketplace tanpa manajemen *account*. Basis data *account* digunakan secara khusus oleh Identity Service. **Marketplace Web Service tidak boleh secara langsung mengakses basis data account untuk melakukan validasi token** (harus melalui Identity Service).

Perhatikan bahwa Anda tidak perlu menggunakan banyak mesin untuk membuat aplikasi ini. Contohnya, pada satu mesin anda bisa menggunakan port 8000 untuk JSP, port 8001 untuk identity service, dan port 8002 untuk marketplace web service.

### Deskripsi Tugas

Kebutuhan fungsional dan non-fungsional tugas yang harus dibuat adalah sebagai berikut.

1. Halaman registrasi, login, catalog, purchase confirmation, your products, add product, edit product, sales, dan purchases seperti pada tugas 1.
2. Marketplace web service dengan fungsi-fungsi sesuai kebutuhan sistem dalam mengakses data (kecuali login, register, dan logout).
3. Identity service yang menangani manajemen *account* seperti login dan register, serta validasi access token.
4. Fitur like tidak perlu diimplementasikan dengan menggunakan AJAX.
3. Tidak perlu melakukan validasi javascript.
4. Tampilan pada tugas ini **tidak akan dinilai**. Sangat disarankan untuk menggunakan asset dan tampilan dari tugas sebelumnya. Boleh menggunakan CSS framework seperti Bootstrap atau javascript library seperti jQuery.
5. Tidak perlu memperhatikan aspek keamanan dan etika penyimpanan data.


### Skenario

#### Login
1. Pengguna mengakses halaman login, contoh: `/login.jsp` dan mengisi form.
2. JSP akan membuka HTTP request ke Identity Service, contoh `POST /login` dengan body data email dan password.
3. Identity service akan melakukan query ke DB untuk mengetahui apakah email dan password tersebut valid.
4. Identity service akan memberikan HTTP response `access token` dan `expiry time` jika email dan password ada di dalam DB, atau error jika tidak ditemukan data. Silakan definisikan `expiry time` yang menurut Anda sesuai.
5. Access token ini digunakan sebagai representasi state dari session pengguna dan harus dikirimkan ketika pengguna ingin melakukan semua aktivitas, kecuali search (catalog), login, register, dan logout. 
6. Access token dibangkitkan secara random. Silakan definisikan sendiri panjang tokennya.
7. Cara penyimpanan access token dibebaskan.
6. Silakan definisikan format request dan response sesuai kebutuhan anda. Anda dapat menggunakan JSON atau XML untuk REST.

#### Register
1. Pengguna mengakses halaman register, contoh: `/register.jsp` dan mengisi form.
2. JSP akan melakukan HTTP request ke Identity Service, contoh `POST /register` dengan body data yang dibutuhkan untuk registrasi.
3. Identity service akan query DB untuk melakukan validasi bahwa email dan username belum pernah terdaftar sebelumnya.
4. Identity service akan menambahkan user ke DB bila validasi berhasil, atau memberi HTTP response error jika username sudah ada atau confirm password salah.
4. Identity service akan memberikan HTTP response `access token` dan `expiry time` dan user akan ter-login ke halaman catalog.
6. Silakan definisikan format request dan response sesuai kebutuhan anda. Anda dapat menggunakan JSON atau XML untuk REST.

#### Logout
1. Pengguna menekan tombol logout.
2. JSP akan melakukan HTTP request ke Identity Service, contoh `POST /logout` dengan body data yang dibutuhkan.
3. Identity service akan menghapus atau melakukan invalidasi terhadap access token yang diberikan.
4. Identity service akan mengembalikan HTTP response berhasil.
5. Halaman di-*redirect* ke halaman login.

#### Add Product, Search Product, Purchase Product dll
1. Pengguna mengakses halaman add product, misal `/add-product.jsp` dan mengisi form.
2. JSP akan memanggil fungsi pada *marketplace web service* dengan SOAP, misalnya `addProduct(access_token, name, description, price, image)`. Contohnya, dapat dilihat pada
[link berikut](http://www.mkyong.com/webservices/jax-ws/jax-ws-hello-world-example/)
Perhatikan pemanggilan pada contoh ini seperti melakukan remote procedure call.
3. Fungsi tersebut akan melakukan HTTP request ke Identity Service, untuk mengenali user dengan `access_token` yang diberikan.
    - Jika `access_token` **kadaluarsa**, maka `addProduct` akan memberikan response expired token.
    - Jika `access_token` **tidak valid**, maka `addProduct` akan memberikan response error ke JSP.
    - Jika `access_token` **valid**, maka `addProduct` akan memasukan produk ke DB, dan memberikan response kesuksesan ke JSP.
4. Jika `access_token` sudah kadaluarsa atau tidak valid (yang diketahui dari response error marketplace web service), sistem akan me-redirect user ke halaman login.
4. Untuk purchase product, like product, unlike product, edit product, delete product, get purchased products, get sold products kira-kira memiliki mekanisme yang sama dengan add product di atas.
5. Silakan definisikan format object request dan response sesuai kebutuhan anda.


#### Bonus
Anda tidak dituntut untuk mengerjakan ini. Tetapi bila Anda cukup dewa (baca=tertantang) silakan selesaikan permasalahan berikut:
- Mekanisme *auto-renew* access token yang tepat sehingga user tidak ter-logout secara paksa saat melakukan serangkaian aktivitas pada sistem dalam waktu yang cukup lama. Access token dapat di generate kembali ketika lifetime dari token tersebut habis. Cara implementasi dibebaskan.


### Penjelasan
Berikut penjelasan mengenai beberapa konsep yang digunakan pada pembuatan tugas ini
1.  Basis data dari sistem. <br>
    Basis data dari sistem website marketplace sederhana yang kelompok buat terdiri dari dua basis data, yakni basis data **_identityservice_** dan basis data **_marketplace_**. Basis data **_identityservice_** berisi informasi pengguna. Basis data ini terdiri dari dua relasi, yakni relasi *user* dan *token*. Relasi *user* berisi informasi mengenai pengguna. Relasi *token* berisi nomor token pengguna ketika mengakses website marketplace dengan batas waktu akses. <br>
    Basis data **_marketplace_** berisi informasi produk dan pembelian. Basis data ini terdiri dari tiga relasi, yakni relasi *product*, *purchase*, dan *likes*. Relasi *product* berisi informasi mengenai produk.
    Relasi *purchase* berisi informasi pembelian, dan terakhir relasi *likes* terdiri dari informasi like pada produk.
2.  Konsep *shared session* dengan menggunakan REST.
    Konsep *shared session* adalah setiap *user* yang berhasil melakukan login dan berhasil mendapatkan token. Token yang diterima oleh *user* tersebut dapat digunakan oleh *user* lain untuk mendapatkan sesi yang sama walaupun berada pada lokasi yang berbeda.

3.  Pembangkitan token dan expire time pada sistem yang anda buat.
    Token pada website marketplace sederhana ini dibangkitkan ketika pengguna berhasil melakukan login atau register. Token tersebut disimpan pada basis data beserta id user. Token merupakan suatu karakter yang diciptakan dari UUID. <br>
    Token dihilangkan ketika pengguna melakukan logout atau waktu sesi sudah habis. Token dihapus dari basis data. Expire time juga dibangkitkan di saat yang bersamaan ketika token dibangkitkan. Expire time dimasukan ke dalam basis data dengan menyimpan waktu sekarang ditambah 10 menit. <br> 
4.  Kelebihan dan kelemahan dari arsitektur aplikasi tugas ini, dibandingkan dengan aplikasi monolitik (login, CRUD DB, dll jadi dalam satu aplikasi) <br>
    Kelebihan dari arsitektur aplikasi ini dibandingkan dengan aplikasi monolitik adalah semua fungsionalitas dibagi ke dalam service sehingga lebih mudah mengatasi satu masalah. Service berjalan secara mandiri sehingga lebih mudah dikelola.
    Kekurangan dari arsitektur aplikasi ini dibandingkan dengan aplikasi monolitik pembuatannya cenderung kompleks dibandingkan dengan aplikasi monolitik.

### Pembagian Tugas

*Harap semua anggota kelompok mengerjakan SOAP dan REST API kedua-duanya*. Tuliskan pembagian tugas seperti berikut ini.

REST :
1. Generate token           : 13514063
2. Validasi token           : 13514043
3. Fungsionaltias Login     : 13514063
4. Fungsionalitas Register  : 13514043
5. Fungsionalitas Logout    : 13514071
6. Fungsionalitas Confirmation Purchase Info User : 13514071
7. Fungsionalitas Catalog Info User : 13514043
8. Fungsionalitas Purchase Info User : 13514043
9. Fungsionalitas Sales Info User : 13514043
10. Fungsionalitas Your Product Info User : 13514043

SOAP :
1. Fungsionalitas Search Catalog    : 13514043
2. Fungsionalitas Show Catalog      : 13514043
3. Fungsionalitas like              : 13514043
4. Fungsionalitas Confirmation Purchase :   13514063
5. Fungsionalitas Show Product       : 13514043
6. Fungsionalitas Show Sales        : 13514043
7. Fungsionalitas Show Purchase     : 13514043
8. Fungsionalitas Add Product       : 13514071
9. Fungsionalitas Edit Product      : 13514071

Web app (JSP) :
1. Halaman Login    : 13514063, 13514071
2. Halaman Register : 13514063, 13514071
3. Halaman Catalog  : 13514043
4. Halaman Confirmation Purchase    :   13514063
5. Halaman Your Product :   13514043
6. Halaman Sales    :   13514043
7. Halaman Purchase :   13514043
8. Halaman Add Product  :   13514071
9. Halaman Edit Product :   13514063, 13514071

## About

Asisten IF3110 2016

Adin | Chairuni | David | Natan | Nilta | Tifani | Wawan | William

Dosen : Yudistira Dwi Wardhana | Riza Satria Perdana
