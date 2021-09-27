package genre;

import java.util.List;
import detail.DetailDTO;

public interface GenreDAOInter {
	
	// 카테고리 : 장르별 전체 리스트
	public List kindList(DetailDTO dto) throws Exception;
	
	// 카테고리 : 장르별 전체 리스트 수(count)
	public int kindCount(int genre) throws Exception;
	
	// 카테고리 : 전체 리스트 수(장르 구별 x - count)
	public int totalCount() throws Exception;
	
	// 카테고리 : 전체 리스트(장르 구별 x)
	public List totalList(DetailDTO dto) throws Exception;
}
