package to.na.akaksr.pocketfi.vo;

import lombok.Data;

@Data
public class PocketFiVO {
	// [사용자 정보]
	private String password;
	
	// [데이터 사용량]
	// 데이터 값
	private String ID_TX_BYTES;
	private String ID_RX_BYTES;
	private String ID_DAILY_TX_BYTES;
	private String ID_DAILY_RX_BYTES;
	private String ID_TOTAL_TX_BYTES;
	private String ID_TOTAL_RX_BYTES;
	
	// [LTE 정보]
	// 데이터 값
	private String LTE_STATUS;
	private String LTE_USE_TIME;
	private String LTE_DIAL;
	private String LTE_IMEI;
	private String LTE_APN_NAME;
	private String LTE_MTU;
	private String LTE_IP_ADDR;
	private String LTE_SUBNETMASK;
	private String LTE_DEF_GATEWAY;
	private String LTE_FST_DNS;
	private String LTE_SEC_DNS;
	
	// [배터리 사용량]
	// 데이터 값
	private String BAT_LEVEL;
	
	// [신호 강도]
	// 데이터 값
	private String ID_SIGNAL_LEVEL;
	
}
