package recommend;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import detail.DetailDTO;

public class PlatformDAOImpl implements PlatformDAOInter {

	@Autowired
	private SqlSessionTemplate mybatis = null;

	@Override
	public List platform() throws Exception {
		
		return mybatis.selectList("recommend.platform");
	}

	@Override
	public List naverList(DetailDTO dto) throws Exception {
		
		return mybatis.selectList("recommend.naverList", dto);
	}

	@Override
	public int naverCount(String link_naver) throws Exception {
		
		return mybatis.selectOne("recommend.naverCount", link_naver);
	}

	@Override
	public List kakaoList(DetailDTO dto) throws Exception {
		
		return mybatis.selectList("recommend.kakaoList", dto);
	}

	@Override
	public int kakaoCount(String link_kakao) throws Exception {
		
		return mybatis.selectOne("recommend.kakaoCount", link_kakao);
	}
	
	
}
