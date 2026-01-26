# Git 용어 정리

**한 줄 요약:** `수정 → Stage → Commit → Push`

## Unstaged
- 파일이 수정되긴 했지만, **이번 커밋에 포함할지 아직 선택하지 않은 상태**
- 예시:
    - README.md를 수정했지만 아직 `git add` 하지 않은 상태
- 상태 확인:
  ```bash
  git status
  ```
  - Unstaged Changes 영역에 표시됨
  
## Staged
- 이 파일은 **이번 커밋에 포함한다**라고 확정한 상태
- 예시:
    - `git add README.md` 후 커밋 대기 상태
- 상태 확인:
  ```bash
  git status
  ```
    - Staged Changes 영역에 표시됨

## Amend
- 마지막 커밋을 수정/보완할 때 사용하는 기능
- 예시:
    - 커밋 메시지 수정:
      ```bash
      git commit --amend -m "수정된 메시지"
      ```
    - 커밋에 누락된 파일 추가
       ```bash
       git add README.md
       git commit --amend --no-edit
       ```
- 주의:
  - **Push 전**에만 안전하게 사용 가능
  - Push 후 사용 시 Force Push 필요 -> 협업 프로젝트에서는 위험**❌사용❌**