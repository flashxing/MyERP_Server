/**
 * �˻�����ӿ�
 */
package com.njue.mis.interfaces;

import java.util.Vector;

import com.njue.mis.model.PortBack;

public interface PortOutServicesHandle
{
	
	/**
	 * ����µ��˻���¼
	 * @param portOut ��װ�õ�portOut����
	 * @return ִ�н��
	 */
	boolean addPortOut(PortBack portOut);
	/**
	 * ��ȡ���е��˻���Ϣ
	 * @return �˻���Ϣ����
	 */
	Vector<PortBack> getAllPortOut();
	/**
	 * ��ѯ���ݿ��������������˻���¼
	 * @param field ��ѯ���ֶ�
	 * @param value �����ֵ
	 * @return ��ѯ���
	 */
	Vector<PortBack> searchPortOut(String field,String value);
	/**
	 * ��ѯ���ݿ��������������˻���¼
	 * @param beginTime ��ѯ�Ŀ�ʼʱ��
	 * @param endTime  ��ѯ�Ľ���ʱ��
	 * @return ��ѯ���
	 */
	Vector<PortBack> searchPortInByTime(String beginTime,String endTime);
	/**
	 * �ж��˻�����Ƿ����
	 * @param id 
	 * @return
	 */
	boolean isExited(String id);
}
