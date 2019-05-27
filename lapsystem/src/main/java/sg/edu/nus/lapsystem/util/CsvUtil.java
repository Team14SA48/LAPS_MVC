package sg.edu.nus.lapsystem.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import sg.edu.nus.lapsystem.model.CompensationClaimHistory;
import sg.edu.nus.lapsystem.model.LeaveHistory;


public class CsvUtil {
	
	public static boolean exportCsv(String filepath,List<LeaveHistory> list)
	{
		boolean isSuccess=false;
	
		FileOutputStream out=null;
		OutputStreamWriter osw=null;
		BufferedWriter bw=null;
		
		try
		{
//		csvFile.createNewFile();
		File file = new File(filepath);
		out=new FileOutputStream(file);
		osw=new OutputStreamWriter(out,"GB2312");
		bw=new BufferedWriter(osw,1024);
		
//		int num=head.size()/2;
//		StringBuffer buffer=new StringBuffer();
//		for(int i=0;i<num;i++) {
//			buffer.append(" ,");
//		}
//		bw.write(buffer.toString()+fileName+buffer.toString());
//		bw.newLine();
		bw.write("Leave Start Date,Leave End Date,Leave Days,Leave Type,Contact Detail,Reason,Work Dissemination,Status");
		bw.newLine();
		isSuccess=true;
		for(LeaveHistory leave:list)
		{
			bw.write(leave.getLeaveStartDate().toString()+",");
			bw.write(leave.getLeaveEndDate().toString()+",");
			bw.write(leave.getLeaveDays()+",");
			bw.write(leave.getLeaveCategory().getLeaveCategory()+",");
			bw.write(leave.getContractDetail()+",");
			bw.write(leave.getAdditionalReasons()+",");
			if(leave.getWorkDissemination()==null) {bw.write(",");}else {
			bw.write(leave.getWorkDissemination()+",");}
			bw.write(leave.getStatus()+"\n");
			//bw.write(leave.getRejectReason()+"test*");
			
		}
		bw.flush();
		

		}
		catch(Exception e)
		{
			
			//isSuccess=false;
		}
		finally {
			
			if(bw!=null)
			{
				try {
					bw.close();
					bw=null;
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			if(osw!=null)
			{
				try
				{
					osw.close();
					osw=null;
				}
				catch (IOException e) {
                    e.printStackTrace();
                } 
				
			}
			 if(out!=null){
	                try {
	                    out.close();
	                    out=null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
		}
		return isSuccess;

	}
	
	public static boolean exportCompensationCsv(String filepath,List<CompensationClaimHistory> list)
	{
		boolean isSuccess=false;
	
		FileOutputStream out=null;
		OutputStreamWriter osw=null;
		BufferedWriter bw=null;
		
		try
		{

		File file = new File(filepath);
		out=new FileOutputStream(file);
		osw=new OutputStreamWriter(out,"GB2312");
		bw=new BufferedWriter(osw,1024);
		

		bw.write("Compensation Duration,Overtime Work From,Overtime Work To,Overtime Work Reason,Apply Date,Status");
		bw.newLine();
		isSuccess=true;
		for(CompensationClaimHistory claim:list)
		{
			bw.write(claim.getCompensationDuration()+",");
			bw.write(claim.getOverTimeWorkDateFrom().toString()+",");
			bw.write(claim.getOverTimeWorkDateTo().toString()+",");
			bw.write(claim.getOverTimeworkReason()+",");
			bw.write(claim.getApplyDate()+",");
			bw.write(claim.getStatus()+"\n");
			
			
		}
		bw.flush();
		

		}
		catch(Exception e)
		{
			
			
		}
		finally {
			
			if(bw!=null)
			{
				try {
					bw.close();
					bw=null;
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			if(osw!=null)
			{
				try
				{
					osw.close();
					osw=null;
				}
				catch (IOException e) {
                    e.printStackTrace();
                } 
				
			}
			 if(out!=null){
	                try {
	                    out.close();
	                    out=null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
		}
		return isSuccess;

	}
	
}


