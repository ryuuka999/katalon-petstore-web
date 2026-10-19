# Katalon PetStore Web - Automation Testing

Ini Final Project Web Automation pake **Katalon Studio** buat ngetes web demo e-commerce [JPetStore](https://petstore.octoperf.com/actions/Catalog.action) 🐾

Yg dipake di project ini:

- **Hybrid Mode** - step test bs dibuka di tab *Manual* ato *Script*, logic tambahan (if/else, parameterized object, variable) ditulis pake Groovy
- **Gk pake Record Web** - semua Test Object dibikin manual pake XPath, capek tp gpp awokawok
- **Positive & Negative test case**
- **Data-Driven Testing (DDT)** - datanya dr CSV di `Data Files/`, trs di-binding ke variable test case lwt Test Suite
- **Global Variable** - URL, username/password, sm timeout ditaro di Execution Profile `default`, jd gk hardcode di script
- **Test Suite** - semua test case dijalanin lwt `TS_Regression_PetStore` & otomatis keluar execution report-nya

## Yg Perlu Disiapin

| Tool | Versi |
|------|-------|
| Katalon Studio | 9.x / 10.x |
| Browser | Google Chrome (yg terbaru aja) |

## Struktur Project

```
katalon-petstore-web/
├── Data Files/
│   ├── data_login_negative.csv / .dat   # DDT login negatif (5 baris)
│   └── data_search_keyword.csv / .dat   # DDT search positif (5 baris)
├── Object Repository/
│   ├── Page_Header/    # Sign In, Sign Out, input & tombol Search
│   ├── Page_Login/     # Username, Password, tombol Login, pesan error
│   ├── Page_Catalog/   # Kategori Fish, produk, hasil search, Add to Cart
│   └── Page_Cart/      # Tabel & item Shopping Cart
├── Profiles/default.glbl        # Global Variable
├── Scripts/                     # Script Groovy tiap test case
├── Test Cases/                  # TC01 - TC05
└── Test Suites/TS_Regression_PetStore.ts
```

## Test Coverage

| ID | Test Case | Tipe | Data | Expected Result |
|----|-----------|------|------|-----------------|
| TC01 | Login_Positive | Positive | Global Variable | Login sukses, link **Sign Out** muncul |
| TC02 | Login_Negative | Negative, DDT (5x) | `data_login_negative.csv` | Muncul pesan *"Invalid username or password. Signon failed."* (kl field kosong ya form login tetep nongol), user gk bs login |
| TC03 | Search_Product_Positive | Positive, DDT (5x) | `data_search_keyword.csv` | Tabel hasil search ada produk yg dicari |
| TC04 | Search_Product_Negative | Negative | Variable test case | Tabel hasil search kosong melompong |
| TC05 | Add_To_Cart_Positive | Positive, E2E | Global Variable | Login > kategori Fish > pilih produk > Add to Cart > item msk ke Shopping Cart |

Total sekali jalan Test Suite ada **13 test run** (1 + 5 + 5 + 1 + 1). 
### Data DDT

`data_login_negative.csv`

| username | password | expected_message |
|----------|----------|------------------|
| wrong_user | wrong_pass | Invalid username or password. Signon failed. |
| j2ee | wrongpass | Invalid username or password. Signon failed. |
| wronguser | j2ee | Invalid username or password. Signon failed. |
| *(kosong)* | j2ee | *(kosong, form login tetep tampil)* |
| j2ee | *(kosong)* | *(kosong, form login tetep tampil)* |

`data_search_keyword.csv`

| keyword | expected_result |
|---------|-----------------|
| fish | Angelfish |
| Bulldog | Bulldog |
| Persian | Persian |
| Iguana | Iguana |
| retriever | Golden Retriever |

## Global Variable (Profile `default`)

| Variable | Ket |
|----------|-----|
| `BASE_URL` | URL halaman katalog JPetStore |
| `LOGIN_URL` | URL halaman login |
| `VALID_USERNAME` | Username yg valid |
| `VALID_PASSWORD` | Password yg valid |
| `TIMEOUT` | Timeout (detik) buat nunggu element |

## Cara Jalaninnya

### Lwt Katalon Studio (GUI)

1. Clone repo ini dl, trs di Katalon Studio pilih **File > Open Project** & buka folder `katalon-petstore-web`
2. Buka **Test Suites > TS_Regression_PetStore**
3. Klik **Run** trs pilih **Chrome**
4. Tunggu smp kelar, abis itu report-nya bs dicek di panel **Reports** ato di folder `Reports/<timestamp>/` (ada HTML, CSV, sm JUnit XML)

### Lwt Console (Katalon Runtime Engine)

```bash
katalonc -noSplash -runMode=console \
  -projectPath="/path/to/katalon-petstore-web/katalon-petstore-web.prj" \
  -testSuitePath="Test Suites/TS_Regression_PetStore" \
  -browserType="Chrome" \
  -executionProfile="default" \
  -retry=0 \
  -apiKey="<KATALON_API_KEY>"
```

## Execution Report

Tiap kali Test Suite dijalanin, report-nya otomatis kesimpen di `Reports/<timestamp>/TS_Regression_PetStore/<timestamp>/`:

- `<timestamp>.html` - report versi HTML
- `<timestamp>.csv` - ringkasan per test case & step
- `JUnit_Report.xml` - format JUnit, bs dipake buat CI

## Referensi

- [Katalon Studio Docs](https://docs.katalon.com/)
- [JPetStore Demo](https://petstore.octoperf.com/)

Udh gitu aja, makasih yg udh mampir awokawokawok 🙏
