package com.example.stock.common.infrastructure.outbox;

public enum OutboxStatus {  // outbox 패턴에서 메시지 처리 상태
    STARTED,    // 처리 시작
    COMPLETED,  // 처리 완료
    FAILED  // 처리 실패
}
