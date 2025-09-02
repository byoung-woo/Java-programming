# ImageFilterApp — Java Swing 이미지 필터 데모

`BufferedImage`를 4분할(사분면)하여 각 영역에 서로 다른 필터(Original / Grayscale / Sepia / Invert)를 적용하는 **자바 스윙 GUI** 프로젝트입니다. 파일 선택으로 이미지를 로드하고, 콤보박스로 필터를 지정하거나 **Random**, **TimerRandom** 버튼으로 랜덤 적용/주기적 랜덤 적용을 할 수 있습니다.

---

## ✨ 주요 기능

- **이미지 로드**: `JFileChooser`를 통해 JPG/PNG/GIF 불러오기
- **4분할 필터**: 
  - 1사분면(좌상): `filterComboBox1` → `originalFilter1`, `applyGrayscaleFilter1`, `applySepiaFilter1`, `applyInvertColorsFilter1`
  - 2사분면(우상): `filterComboBox2` → `...Filter2`
  - 3사분면(좌하): `filterComboBox3` → `...Filter3`
  - 4사분면(우하): `filterComboBox4` → `...Filter4`
- **Apply Filter**: 콤보박스 설정대로 즉시 적용
- **Random Filter**: 4개 사분면에 무작위 필터 1회 적용
- **TimerRandom Filter**: 1초 간격으로 무작위 필터를 반복 적용 (토글 방식: 한 번 더 누르면 중지)

---

## 🖼️ 화면 구성

- 상단(NORTH): 4개의 필터 콤보박스
- 중앙(CENTER): `JLabel` + `ImageIcon`으로 결과 이미지 표시
- 하단(SOUTH): `Apply Filter`, `Random Filter`, `TimerRandom Filter` 버튼

---

## 🏗️ 빌드 & 실행

### 요구 사항
- JDK 8 이상 (권장: 11+)
- OS 제약 없음(Windows/macOS/Linux)

### 컴파일
```bash
# 파일명: ImageFilterApp.java
javac -encoding UTF-8 ImageFilterApp.java
```

### 실행
```bash
java ImageFilterApp
```

### 첫 실행 흐름
1. 앱 시작 시 파일 선택 대화상자가 열립니다.
2. 이미지를 선택하면 `originalImage`가 로드되고, 동일 크기의 `filteredImage`가 초기화됩니다.
3. 콤보박스를 고른 뒤 **Apply Filter**를 누르거나, **Random/TimerRandom** 버튼으로 테스트하세요.

> **Tip**: 파일 선택을 취소하면 이미지가 로드되지 않으므로, 필터 버튼을 누르기 전에 반드시 이미지를 로드하세요.

---

## 🧪 필터 동작 개요

- **Grayscale**: `0.299*R + 0.587*G + 0.114*B` 가중치 합으로 회색조 변환
- **Sepia**: 
  - `tr = 0.393R + 0.769G + 0.189B`  
  - `tg = 0.349R + 0.686G + 0.168B`  
  - `tb = 0.272R + 0.534G + 0.131B`  
  - 각 채널을 `255`로 클램핑
- **Invert**: 각 채널을 `255 - channel`로 반전

모든 필터는 지정된 사분면의 `(x, y)` 범위 안에서만 `setRGB()`로 픽셀을 갱신합니다.

---

## 🧩 사분면 좌표 범위

가로 W, 세로 H라 할 때:

- **1사분면(좌상)**: `x: [0, W/2), y: [0, H/2)`  
- **2사분면(우상)**: `x: [W/2, W), y: [0, H/2)`  
- **3사분면(좌하)**: `x: [0, W/2), y: [H/2, H)`  
- **4사분면(우하)**: `x: [W/2, W), y: [H/2, H)`

---

## ⏱️ 타이머 동작

- **TimerRandom Filter** 버튼은 `java.util.Timer`로 1초 주기 `TimerTask`를 등록하여 `RandomButton()`을 호출합니다.
- 토글 방식: 실행 중일 때 다시 누르면 `timer.cancel()`로 중지 후 플래그를 업데이트합니다.

> **권장 개선**: `Swing` 환경에서는 `javax.swing.Timer`를 사용하면 이벤트 디스패치 스레드(EDT)에서 안전하게 UI 갱신이 가능합니다.

---

## ⚠️ 주의/개선 포인트

1. **이미지 미선택(취소)**  
   - 현재 코드에서는 파일 선택을 취소하면 `originalImage == null` 상태입니다. 이후 `Random Filter`/`TimerRandom`을 누르면 `getRGB()` 호출에서 NPE 위험이 있습니다.  
   - **개선**: 이미지 로드 전에는 버튼을 비활성화하거나 `originalImage == null` 체크로 가드하세요.

2. **랜덤 인덱스 길이 참조**  
   - `functions2/3/4`의 `randomIndex` 계산에서 `functions1.length`를 사용합니다. 지금은 길이가 모두 4로 같아 문제 없지만, **각 배열의 length를 사용**하는 방식이 안전합니다.

3. **알파 채널**  
   - 현재 `TYPE_INT_RGB` 기반이라 알파가 필요하지 않지만, 만약 `TYPE_INT_ARGB`로 바꾸면 `0xFF << 24`을 포함한 `0xAARRGGBB` 형태로 `setRGB`하는 것이 좋습니다.  
   - 예: `int argb = (0xFF << 24) | (r << 16) | (g << 8) | b;`

4. **성능**  
   - 고해상도 이미지에서 픽셀 루프가 느릴 수 있습니다. `BufferedImageOp(ColorConvertOp/RescaleOp)` 혹은 `Raster`/`LookupTable` 기반 최적화를 검토할 수 있습니다.

5. **스레드 안전**  
   - `java.util.Timer`는 별도 스레드에서 콜백을 실행합니다. UI 업데이트는 반드시 EDT에서 처리해야 하므로 `SwingUtilities.invokeLater(...)`로 래핑하는 것을 권장합니다.

6. **초기화 시점**  
   - `initializeFilteredImage()`는 `originalImage`가 로드된 후 호출되어야 합니다(현재 흐름은 적절). 이미지 교체 로직을 넣는다면 매번 **동일 크기/타입으로 재생성** 필요.

---

## 📁 파일 구조(단일 파일)

```
ImageFilterApp.java
```

핵심 메서드
- `loadImage1()` : 파일 선택 → `originalImage` 로드
- `initializeFilteredImage()` : `filteredImage` 생성
- `applyFilter()` : 콤보박스 값에 따라 4분면 필터 적용
- `RandomButton()` : 한 번 랜덤 적용
- `TimerRandomButton()` : 1초 간격 랜덤 토글
- `originalFilter{1..4} / apply{Grayscale|Sepia|Invert}Filter{1..4}()` : 사분면별 처리
- `updateUI()` : `ImageIcon`으로 표시/갱신

---

## 🔍 트러블슈팅

- **이미지가 보이지 않음**: 파일 선택을 취소했는지 확인. 다시 실행하여 이미지를 선택하세요.
- **버튼 반응 없음**: 이미지 로드 전에 눌렀는지 확인. 가드 코드/버튼 비활성화를 고려하세요.
- **UI가 버벅거림**: 큰 이미지에서는 처리 시간이 필요합니다. 타이머 주기를 늘리거나 최적화를 적용하세요.

---

## 라이선스

교육/실습 용도. 외부 배포 시 라이선스를 명시하세요(예: MIT).

