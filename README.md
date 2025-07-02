# AntiNganggur - Aplikasi Pencari Kerja IT

Selamat datang di repositori AntiNganggur!
Aplikasi mobile ini dirancang untuk menjadi jembatan antara talenta digital dan perusahaan teknologi di Indonesia. Dengan fitur-fitur cerdas, AntiNganggur membantu Anda menemukan pekerjaan impian di dunia IT secara lebih mudah, cepat, dan efisien.

## 🚀 Apa itu Aplikasi AntiNganggur?

AntiNganggur adalah platform digital yang bertujuan mengatasi permasalahan pengangguran di sektor teknologi. Dengan aplikasi ini, para pencari kerja dapat:

✅ Membangun profil profesional

✅ Menjelajahi lowongan kerja terkini

✅ Melamar langsung lewat aplikasi

✅ Memperluas kesempatan di dunia kerja digital

## ✨ Fitur-fitur Utama

🔍 Pencarian Lowongan Cerdas
Cari pekerjaan berdasarkan posisi, lokasi, atau kata kunci favorit Anda.

🔥 Lowongan Terbaru & Trending
Pantau info pekerjaan paling baru dan yang sedang banyak diminati.

🧑‍💼 Profil Profesional Digital
Lengkapi data diri, pengalaman kerja, pendidikan, dan unggah CV Anda.

📩 Lamar Instan
Satu klik untuk mengirim lamaran ke pekerjaan yang Anda incar.

📃 Detail Pekerjaan Lengkap
Telusuri deskripsi, kualifikasi, dan benefit setiap lowongan.

## 🔧 Cara Instalasi

Anda bisa menjalankan aplikasi ini melalui dua cara: menginstal file `.apk` yang sudah jadi atau menjalankan proyek dari *source code*-nya.

### Metode 1: Instalasi via APK (Rekomendasi Cepat)

Metode ini adalah cara termudah untuk mencoba aplikasi di perangkat Android Anda.

1.  **Unduh File APK**
    * Buka halaman **Releases** di repositori GitHub ini.
    * Unduh file dengan nama `app-release.apk`.

2.  **Izinkan Instalasi dari Sumber Lain**
    * Pindahkan file `.apk` yang telah diunduh ke ponsel Anda.
    * Buka File Manager, cari, dan ketuk file tersebut.
    * Jika muncul peringatan keamanan, izinkan instalasi dari "sumber tidak dikenal" melalui menu Pengaturan.

3.  **Install Aplikasi**
    * Ketuk kembali file `.apk` dan pilih **Install**.
    * Setelah selesai, aplikasi AntiNganggur akan muncul di daftar aplikasi Anda.

### Metode 2: Jalankan dari Source Code (Untuk Developer)

Metode ini ditujukan bagi Anda yang ingin mengoprek, memodifikasi, atau berkontribusi pada proyek ini.

**Persyaratan:**
* Android Studio (versi terbaru direkomendasikan).
* Git terinstal di komputer Anda.

**Langkah-langkah:**

1.  **Clone Repositori**
    Buka terminal atau Command Prompt, lalu jalankan perintah:
    ```bash
    git clone [https://github.com/Velazta/AntiNganggur-Mobile-App.git](https://github.com/Velazta/AntiNganggur-Mobile-App.git)
    ```
    Atau unduh dalam bentuk ZIP dari halaman utama repositori.

2.  **Buka Proyek di Android Studio**
    * Buka Android Studio.
    * Pilih **File > Open** (atau "Open an Existing Project").
    * Arahkan ke direktori tempat Anda menyimpan proyek, lalu klik **OK**.

3.  **Sinkronisasi Gradle**
    * Biarkan Android Studio melakukan sinkronisasi Gradle untuk pertama kalinya. Proses ini akan mengunduh semua *library* dan dependensi yang dibutuhkan oleh proyek.

4.  **Jalankan Aplikasi**
    * Setelah sinkronisasi selesai, pilih emulator atau perangkat fisik yang terhubung.
    * Klik tombol **Run** (ikon segitiga hijau ▶️). Aplikasi akan otomatis ter-build dan terinstal.

## サーバー **Penting: Menjalankan Server Backend**

Aplikasi mobile ini membutuhkan koneksi ke server backend agar dapat berfungsi sepenuhnya (mengambil data lowongan, login, register, dll.). Anda **wajib** menjalankan server backend secara lokal di komputer Anda.

Backend aplikasi ini dibuat menggunakan Laravel. Ikuti langkah-langkah berikut untuk menjalankannya:

1.  **Clone Repositori Server**
    Buka terminal baru dan jalankan perintah:
    ```bash
    git clone [https://github.com/Velazta/antinganggur-beta.git](https://github.com/Velazta/antinganggur-beta.git)
    ```

2.  **Instalasi Dependensi**
    Masuk ke direktori `antinganggur-beta` dan install semua dependensi yang dibutuhkan menggunakan Composer.
    ```bash
    cd antinganggur-beta
    composer install
    ```

3.  **Konfigurasi Environment**
    Salin file `.env.example` menjadi `.env` dan konfigurasikan koneksi database Anda di dalamnya.
    ```bash
    cp .env.example .env
    ```
    Jangan lupa untuk membuat database baru sesuai dengan nama yang Anda atur di file `.env`.

4.  **Generate Key dan Migrasi Database**
    Jalankan perintah berikut untuk men-generate *application key* dan membuat tabel-tabel di database.
    ```bash
    php artisan key:generate
    php artisan migrate
    ```

5.  **Jalankan Server Laravel**
    Setelah semua siap, jalankan server lokal dengan perintah:
    ```bash
    php artisan serve
    ```
    Server akan berjalan di `http://127.0.0.1:8000`. Pastikan URL ini sesuai dengan yang ada di konfigurasi aplikasi mobile Anda.


## 👥 Tim Pengembang

Aplikasi ini dikembangkan dan dikelola oleh:

* **Ravelin Luthhan** - (L0123118)
* **Rizky Amalia** - (L0123123)
* **Rafi Amirudin** - (L0123113)

Terima kasih sudah mengunjungi!
Mari bersama bantu kurangi pengangguran di bidang IT lewat teknologi.
⭐ Jangan lupa beri bintang pada repositori ini jika Anda suka!
