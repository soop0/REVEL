package recommend;

import java.util.List;
import detail.DetailDTO;

public interface PlatformDAOInter {
	
		//카테고리 : 네이버시리즈, 카카오페이지 전체 리스트
		public List platform() throws Exception;
		
		//카테고리 : 네이버시리즈 전체 리스트
		public List naverList(DetailDTO dto) throws Exception;
		
		//카테고리 : 네이버시리즈 전체 리스트 수(count)
		public int naverCount(String link_naver) throws Exception;
		
		//카테고리 : 카카오페이지 전체 리스트
		public List kakaoList(DetailDTO dto) throws Exception;
				
		//카테고리 : 카카오페이지 전체 리스트 수(count)
		public int kakaoCount(String link_kakao) throws Exception;
		
	
}
