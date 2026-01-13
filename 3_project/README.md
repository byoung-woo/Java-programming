# ImageFilterApp — 자바 수업 프로젝트 (Java Swing 이미지 필터 데모)

> **프로젝트 출처:** 본 애플리케이션은 **자바 수업 시간에 만든 프로젝트**입니다.

한 장의 이미지를 **4개 사분면**으로 분할하여 각 영역에 서로 다른 필터(Original / Grayscale / Sepia / Invert)를 적용하고, **무작위(Random)** 및 **주기적(Random Timer, 1초)** 변환으로 조합을 탐색할 수 있는 Java Swing 데모입니다.

---

## 1. 문제 정의 (Problem Definition)

- 모바일과 같은 **작은 화면 환경**에서 원본과 필터 적용 이미지를 **두 장**으로 나란히 비교하기는 불편함.
- 사용자는 다양한 필터로 **개성**을 표현하고 싶지만, **여러 결과를 한눈에 비교**하기 어려움.
- 반복적인 필터 전환은 **인지적 부담**과 클릭 수를 증가시키며, 선호 조합을 결정하기까지 시간이 오래 걸림.

**문제식:**  
작은 화면에서도 한 장의 이미지로 **많은 필터 결과를 동시에** 비교할 수 있고, **빠르게** 선호 조합을 찾도록 만드는 UI/기능이 필요하다.

---

## 2. 연구 동기 (Motivation)

- 사진 기반 SNS/메신저 환경에서 **개성 표현**과 **간편한 편집**의 수요 증가.
- 원본-결과 비교의 **시각적 비용**과 **전환 비용**을 줄이는 UI가 사용자 경험을 개선할 수 있음.
- 무작위/주기적 변환은 **탐색의 재미**와 영감(serendipity)을 제공하여 실험적 사용을 유도.

---

## 3. 해결 전략 및 설계 (Approach & Design)

### 3.1 핵심 아이디어
- 한 장의 이미지를 **사분면 분할**하여 각 구역에 **서로 다른 필터**를 적용 → 한 화면에서 **동시 비교**.
- **콤보박스**로 구역별 필터 선택, **Random** 버튼으로 즉시 무작위 조합, Random Timer(1초)로 지속 탐색.

### 3.2 UI & 동작 개요
- 상단: 4개의 필터 콤보박스  
- 중앙: `JLabel(ImageIcon)`으로 결과 표시  
- 하단: `Apply Filter`, `Random Filter`, `TimerRandom Filter` 버튼

### 3.3 구현 핵심
- `JFileChooser`로 이미지 로드 → `originalImage` / `filteredImage` 준비
- 각 사분면 좌표 범위에 대해 픽셀 단위 변환(`getRGB/setRGB`):
  - **Grayscale**: `0.299R + 0.587G + 0.114B`
  - **Sepia**: `tr=0.393R+0.769G+0.189B`, `tg=0.349R+0.686G+0.168B`, `tb=0.272R+0.534G+0.131B` (255 클램핑)
  - **Invert**: `255 - channel`
- 무작위/타이머: `Random` 즉시 1회, `Random Timer`는 1초 간격 반복(토글)

---

## 4. 기대 효과 (Expected Outcomes)

- 작은 화면에서도 **동시 비교**로 전환 비용 감소 → **결정 시간 단축**.
- **무작위/주기적 탐색**으로 실험성·재미 증가 → **몰입도 향상**.
- 한 장의 이미지에서 **개성 있는 조합**을 직관적으로 구성.

---

## 5. 시스템 요구 사항 (Requirements)

- **JDK**: 8 이상(권장 11+)
- **OS**: Windows / macOS / Linux
- **라이브러리**: 표준 Java SE (Swing/2D)

---

## 6. 빌드 & 실행 (Build & Run)

```bash
# 파일명: ImageFilterApp.java
javac -encoding UTF-8 ImageFilterApp.java
java ImageFilterApp
```

**첫 실행 흐름:** 앱 시작 → 파일 선택 → 사분면별 필터 선택/Random/Timer 활용

---

## 7. 한계 및 향후 과제 (Limitations & Future Work)

- **성능 최적화**: 초고해상도 이미지에서 픽셀 루프 비용 증가
- **레이아웃 확장**: 3/6/9분할, 사용자 지정 그리드
- **필터 확장**: 밝기/대비/감마/블러/샤픈, LUT 기반 효과
- **저장/공유**: 결과 이미지 저장(JPEG/PNG), 구역별 프리셋 관리

---

## 8. 발표자료

자료 링크 : https://drive.google.com/file/d/1HbOs4geWv3aCNEh5nv9JB0sj6vdgkwjJ/view?usp=sharing