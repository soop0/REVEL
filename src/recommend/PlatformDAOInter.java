package recommend;

import java.util.List;
import detail.DetailDTO;

public interface PlatformDAOInter {
	
		//ī�װ� : ���̹��ø���, īī�������� ��ü ����Ʈ
		public List platform() throws Exception;
		
		//ī�װ� : ���̹��ø��� ��ü ����Ʈ
		public List naverList(DetailDTO dto) throws Exception;
		
		//ī�װ� : ���̹��ø��� ��ü ����Ʈ ��(count)
		public int naverCount(String link_naver) throws Exception;
		
		//ī�װ� : īī�������� ��ü ����Ʈ
		public List kakaoList(DetailDTO dto) throws Exception;
				
		//ī�װ� : īī�������� ��ü ����Ʈ ��(count)
		public int kakaoCount(String link_kakao) throws Exception;
		
	
}
