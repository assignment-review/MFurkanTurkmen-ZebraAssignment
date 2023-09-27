
---
- **ZebraAssignment** adinda bir veritabanı olusturulmali veya kullanacagimiz 
veritabanimizi application.yml icerisinde belirtmeliyiz.
- yine yaml dosyası icerisinde postgresql icin: **username ve password** kisimlarimizi
ayarlamamiz gerekiyor.
---
### Deneme islemleri kolay olmasi icin regex eklenmedi.
### yukarida adimlar tamamlandi ise
### sistem 9090 portundan ayaga kalkmaktadir.
### http://localhost:9090/swagger-ui/index.html

---
### Book Document
<p align="center">
<img src="book_document.png">
</p>

- ayni ISBN'e sahip kitaplar olamaz.
- bir kitap ödünç alinmis ise kitap silinemez.
- güncelleme kisminda ister ISBN isterseniz ID ile güncelleme yapabilirsiniz.

---
### Member Document
<p align="center">
<img src="member_document.png">
</p>

- kitap kopyasi 0 ise üye bu kitabı ödünc alamaz.
- ayni mail adresine ait üyeler olamaz.
- get all member da tüm üyeler gösterilir.
- get all borrower da sadece kitap ödünç almış olanlar gösterilir.
- üyeye ait ödünç alinmiş kitaplar var ise üye silme isleminde
kitap kopyalari iade edilir ve kullanici silinir.
