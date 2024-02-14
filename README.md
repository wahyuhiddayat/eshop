# Tutorial 1

[![Coverage](.github/badges/jacoco.svg)](https://github.com/wahyuhiddayat/main/actions/workflows/jacoco.yml)

## Table of Contents
- [Tutorial 1: Coding Standards](#tutorial-1)
- [Tutorial 2: CI/CD DevOps](#tutorial-2)


## Reflection 1

**Meaningful Names**

Dengan penerapan nama yang jelas dan mudah dipahami untuk variabel, metode, dan kelas, saya telah memastikan bahwa setiap penamaan dalam kode dapat dimengerti dengan jelas dan tidak membingungkan.

**Function**

Fungsi-fungsi dalam kode saya dirancang untuk bersifat singkat, memiliki nama yang deskriptif, dan terorganisasi dengan baik. Saya memastikan agar setiap fungsi fokus pada satu tujuan saja, mengoptimalkan kejelasan dan keefisienan.

**Comments**

Penggunaan komentar telah saya minimalisir dengan mengutamakan kode yang rapi, intuitif, dan menggunakan penamaan yang eksplisit, sehingga meningkatkan kejelasan dan kemudahan pemahaman bagi pembaca kode.

**Objects and Data Structure**

Saya telah menerapkan prinsip enkapsulasi dengan baik dengan mengimplementasikan _getter_ dan _setter_ dengan baik pada objek `Product`. Struktur data yang saya gunakan untuk menyimpan produk adalah `List<Product>` dalam `ProductRepository`. Namun, cara ini bukan cara yang paling efisien jika terdapat banyak objek `Product`.

**Error Handling**

Saya telah menerapkan strategi penanganan kesalahan dasar, seperti pengalihan pengguna ke halaman daftar ketika produk tidak ditemukan.

## Reflection 2

**Nomor 1**

Setelah membuat unit test, saya merasa lebih yakin dengan kualitas dan kebenaran kode yang saya buat karena unit test membantu memverifikasi bahwa setiap bagian dari program berfungsi seperti yang diharapkan dengan negative scenario maupun positive scenario.

Jumlah unit test yang harus dibuat dalam sebuah kelas biasanya setiap fungsi atau method memiliki setidaknya satu unit test dengan negative maupun positive scenario.

Kecukupan unit test dapat diukur dengan code coverage. Code coverage memberikan gambaran tentang seberapa banyak kode yang telah diuji oleh unit test yang dibuat. Akan tetapi, sebuah program yang memiliki 100% code coverage walaupun memang memberi indikasi bahwa setiap baris kode telah diuji, tidak menjamin bahwa program tersebut bebas dari bug atau kesalahan. 100% code coverage hanya menunjukkan bahwa semua baris kode telah dilalui oleh test, tetapi tidak menilai kualitas dari assertion yang digunakan atau mempertimbangkan semua kemungkinan kombinasi input dan kondisi yang bisa terjadi.

**Nomor 2**

Pembuatan suite test fungsional baru untuk memverifikasi jumlah item dalam daftar produk dengan pendekatan yang sama dengan `CreateProductFunctionalTest.java` bisa menyebabkan beberapa masalah terkait dengan clean code, seperti dupliksi kode dan ketergantungan yang tinggi. Untuk meningkatkan kebersihan kode dan mengurangi masalah ini, beberapa perbaikan yang bisa dilakukan adalah refactoring dan penggunaan @BeforeEach atau @BeforeAll