# Advanced Programming 

## Table of Contents
- [Tutorial 1: Coding Standards](#tutorial-1)
- [Tutorial 2: CI/CD DevOps](#tutorial-2)

## Tutorial 1

### Reflection 1
Saya sudah menerapkan prinsip clean code seperti:
1. Meaningful Names : Dengan penerapan nama yang jelas dan mudah dipahami untuk variabel, metode, dan kelas, saya telah memastikan bahwa setiap penamaan dalam kode dapat dimengerti dengan jelas dan tidak membingungkan.
2. Function : Fungsi-fungsi dalam kode saya dirancang untuk bersifat singkat, memiliki nama yang deskriptif, dan terorganisasi dengan baik. Saya memastikan agar setiap fungsi fokus pada satu tujuan saja, mengoptimalkan kejelasan dan keefisienan.
3. Comments : Penggunaan komentar telah saya minimalisir dengan mengutamakan kode yang rapi, intuitif, dan menggunakan penamaan yang eksplisit, sehingga meningkatkan kejelasan dan kemudahan pemahaman bagi pembaca kode.
4. Objects and Data Structure : Saya telah menerapkan prinsip enkapsulasi dengan baik dengan mengimplementasikan _getter_ dan _setter_ dengan baik pada objek `Product`. Struktur data yang saya gunakan untuk menyimpan produk adalah `List<Product>` dalam `ProductRepository`. Namun, cara ini bukan cara yang paling efisien jika terdapat banyak objek `Product`.
5. Error Handling : Saya telah menerapkan strategi penanganan kesalahan dasar, seperti pengalihan pengguna ke halaman daftar ketika produk tidak ditemukan.

### Reflection 1

**Nomor 1**

Setelah membuat unit test, saya merasa lebih yakin dengan kualitas dan kebenaran kode yang saya buat karena unit test membantu memverifikasi bahwa setiap bagian dari program berfungsi seperti yang diharapkan dengan negative scenario maupun positive scenario.

Jumlah unit test yang harus dibuat dalam sebuah kelas biasanya setiap fungsi atau method memiliki setidaknya satu unit test dengan negative maupun positive scenario.

Kecukupan unit test dapat diukur dengan code coverage. Code coverage memberikan gambaran tentang seberapa banyak kode yang telah diuji oleh unit test yang dibuat. Akan tetapi, sebuah program yang memiliki 100% code coverage walaupun memang memberi indikasi bahwa setiap baris kode telah diuji, tidak menjamin bahwa program tersebut bebas dari bug atau kesalahan. 100% code coverage hanya menunjukkan bahwa semua baris kode telah dilalui oleh test, tetapi tidak menilai kualitas dari assertion yang digunakan atau mempertimbangkan semua kemungkinan kombinasi input dan kondisi yang bisa terjadi.

**Nomor 2**

Pembuatan suite test fungsional baru untuk memverifikasi jumlah item dalam daftar produk dengan pendekatan yang sama dengan `CreateProductFunctionalTest.java` bisa menyebabkan beberapa masalah terkait dengan clean code, seperti dupliksi kode dan ketergantungan yang tinggi. Untuk meningkatkan kebersihan kode dan mengurangi masalah ini, beberapa perbaikan yang bisa dilakukan adalah refactoring dan penggunaan @BeforeEach atau @BeforeAll

## Tutorial 2

**Nomor 1**

Beberapa isu yang saya perbaiki dalam kode saya di antaranya adalah:

1. ProductControllerTest.java
    
    Saya sebelumnya tidak memiliki unit test untuk controller (ProductController.java) dan saya membuat nya dengan menggunakan mock objects, menguji kasus yang beragam seperti psotive scenario, edge case scenario, negative scenario untuk memastikan kode bekerja dalam berbagai kondisi, menggunakan assertion untuk memverifikasi unit test return value yang diharapkan.

    Selain itu, saya juga mengganti kode import yang awalnya menggunakan wildcard dengan mengimportnya satu-satu untuk bagian yang dibutuhkan saja. 

2. ProductServiceTest.java

    Sebelumnya, unit test untuk service (ProductService.java) saya tidak lengkap dan tidak menguji seluruh method yang ada. Saya menambahkan unit test untuk menguji method findAll, create, dan findById.

    Selain itu, saya juga menghapus public modifiersnya yang awalnya secara explicit menuliskan `public` karena interface by default akan memiliki sifat `public`.

3. ProductRepositoryTest.java

    Unit test yang saya buat dalam tutorial 1 tidak mencakup keseluruhan method yang berada dalam repository (ProductRepositoryTest.java). Oleh karena itu, saya menambahkan unit test untuk update product, find by id, delete product, dengan masing-masing terdapat positive dan negative scenarionya. Selain itu, saya juga menghapus import yang tidak digunakan.

    Saya juga menambahkan kode agar method create, update, dan delete bisa menghandle jika productnya bernilai null di ProductRepository.java dan menambahkan unit testnya juga di ProductRepositoryTest.java.

4. EshopApplicationTest.java

    EshopApplication.java sebelumnya tidak mempunyai unit test, oleh karena itu saya membuat unit testnya yang berfokus pada verifikasi aplikasi Spring Boot menggunakan smoke test, yaitu dengan memastikan bahwa aplikasi dapat dijalankan tanpa error melalui pemanggilan method main.

**Nomor 2**

Menurut saya, saya sudah menerapkan konsep Continuous Integration (CI) dan Continuous Deployment (CD) dalam proyek saya. Saya telah menambahkan sebuah workflow yang secara otomatis terpicu setiap kali saya melakukan push perubahan kode ke dalam repositori saya. Proses ini tidak hanya memastikan kode saya selalu teruji melalui unit tests, tetapi juga memungkinkan aplikasi saya untuk dideploy secara otomatis ke lingkungan produksi tanpa hambatan. Dengan demikian, integrasi dan penerapan perubahan kode menjadi lebih efisien dan berkurang risiko kesalahan, yang pada gilirannya meningkatkan kualitas dan keandalan aplikasi secara keseluruhan. 