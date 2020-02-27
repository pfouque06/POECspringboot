package com.firstspringstartproject02.service;

import java.util.List;

//import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firstspringstartproject02.dao.CommentaireRepository;
import com.firstspringstartproject02.model.Commentaire;

@Service(value = "commentaireService")
public class CommentaireServiceImpl implements CommentaireService {
	
    @Autowired
    private CommentaireRepository commentaireRepository;

	@Override
	public List<Commentaire> getAllCommentaire() {
		//return IteratorUtils.toList(commentaireRepository.findAll().iterator());
		return commentaireRepository.findAll();
	}

	@Override
	public Commentaire getCommentaireById(Long id) {
		Commentaire result = commentaireRepository.findById(id).get();
		return result;
	}

	@Override
	@Transactional(readOnly=false)
	public Commentaire saveOrUpdateCommentaire(Commentaire commentaire) {
		return commentaireRepository.save(commentaire);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteCommentaire(Long id) {
		commentaireRepository.deleteById(id);
		
	}

	

}
