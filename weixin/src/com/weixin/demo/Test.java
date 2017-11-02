package com.weixin.demo;


import net.sf.json.JSONObject;

public class Test {
public static void main(String[] args) {
	String jsonStr;
	String s = "{RETN=000000,IMSI=460013650606441, MSISDN=8618603658937, SubType=2, NAM=0, MSFg=0, MSType=10, SubRest=0, CSPri=0, NAEA=000000, Tele=1, EmegCall=1, SMMO=1, SMMT=1, Fac3=0, AutoFac3=0, Fac4=0, BOC=0, BIC=0, BSS=0, BR=0, BPR=0, BFN=0, BCT=0, BICT=0, BFICT=0, BT=0, BIP=0, BPOS=0, ODBPL_3=0, ODBPL_4=0, GenCDS=1,RCType=0, AddRCType=0, ARD=0, ODBNC=0, VGCS=0, VBS=0, ZCSet=65535, STYPE=0, OFAID=255, SIPID = 0, BsgCount=3, Bsg1=0, Bsg2=1, Bsg3=4, BCID=0, State=1, LocInfo=1, TIFFlag=0, TIFNC=0, TrigTCSI=1, OCount=1, OPhase=2, OTDP1=2, OSK1=VPMN, OSCF1=8613000974, ODC1=0, OCTFg1=0, TCount=1, TPhase=2, TTDP1=12, TSK1=VPMN, TSCF1=8613000974, TDC1=0, TCTFg1=0, CLIP_P=1, CLIPOpt=0, CLIR_P=0, COLP_P=0, COLR_P=0, CH_P=1, CW_P=1, CW_A=1, CFU_P=1, CFU_R=0, CFU_A=0, CFU_O=0, CFUNTC=0, CFURDP=0, CFB_P=1, CFB_R=0, CFB_A=0, CFB_O=0, CFBNTC=0, CFBNTF=0, CFBRDP=0, CFNRY_P=1, CFNRY_R=0, CFNRY_A=0, CFNRY_O=0, CFNRYNTC=0, CFNRYNTF=0, CFNRYRDP=0, CFNRYTime=20, CFNRC_P=1, CFNRC_R=1, CFNRC_A=1, CFNRC_O=1, CFNRCNTC=0, CFNRCRDP=0, CFNRCNum=864511163115001, CBCtrl=0}";
	String a = s.replace("=", ":");
	jsonStr = a.replace("{", "{\"");
	jsonStr = jsonStr.replace(":", "\":\"");
	jsonStr = jsonStr.replace(",", "\",\"");
	jsonStr = jsonStr.replace("}", "\"}");
	JSONObject js = JSONObject.fromObject(jsonStr);
	System.out.println(js.getString(" MSFg"));
	}
}
	
